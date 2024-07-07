package com.pulse.social_graph.listener.kafka.outbox.member;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pulse.event_library.event.OutboxEvent;
import com.pulse.member.grpc.MemberProto;
import com.pulse.social_graph.controller.grpc.GrpcMemberClient;
import com.pulse.social_graph.listener.spring.event.MemberCreateEvent;
import com.pulse.social_graph.util.TraceUtil;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

/**
 * 회원이 생성되면 이벤트를 수신하는 Kafka 리스너
 * zeroPayload - gRPC 클라이언트를 통해 member 서버에 회원 정보를 요청한다.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberCreateMessageListener {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final GrpcMemberClient grpcMemberClient;
    private final Tracer tracer = GlobalOpenTelemetry.getTracer("content-kafka-consumer");
    private final TraceUtil traceUtil;

    /**
     * 유저가 생성되면 이벤트를 수신하고
     * gRPC 클라이언트를 통해 member 서버에 회원 정보를 요청한다.
     *
     * @param record
     * @param acknowledgment
     * @param partition
     * @param offset
     */
    @KafkaListener(
            topics = {"member-created-outbox"},
            groupId = "content-group-member-create",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenMemberCreate(
            ConsumerRecord<String, String> record,
            Acknowledgment acknowledgment,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) throws JsonProcessingException {

        // 1. Context를 추출한다.
        Context extractedContext = traceUtil.extractContextFromRecord(record);

        // 2. 기존 Trace를 기반으로 새로운 Span을 생성
        Span span = tracer.spanBuilder("KafkaListener Process Message - Content")
                .setParent(extractedContext)  // 기존 Trace의 Context를 부모로 설정
                .startSpan();

        // 3. Span을 현재 컨텍스트에 설정한다.
        try (Scope scope = span.makeCurrent()) {
            // 3-1. Kafka 메시지를 OutboxEvent로 변환한다.
            String jsonValue = record.value();
            OutboxEvent event = objectMapper.readValue(jsonValue, MemberCreateEvent.class);

            // 3-2. gRPC 클라이언트를 통해 member 서버에 회원 정보를 요청한다.
            MemberProto.MemberRetrieveResponse result =
                    grpcMemberClient.getMemberById(event.getId(), extractedContext);

            log.info(String.valueOf(result));
        } catch (Exception e) {
            // 4. 에러 발생 시 에러를 클라이언트에게 전달
            span.recordException(e);
            throw e;
        } finally {
            // 5. span 종료
            span.end();
            log.info("Ending traceId: {}, spanId: {}", span.getSpanContext().getTraceId(), span.getSpanContext().getSpanId());
        }

        // 6. ack 처리
        acknowledgment.acknowledge();
    }

}
