package com.pulse.social_graph.adapter.in.kafka.member;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pulse.member.grpc.MemberProto;
import com.pulse.social_graph.adapter.out.event.NicknameChangeEvent;
import com.pulse.social_graph.adapter.out.event.outbox.OutboxEvent;
import com.pulse.social_graph.application.port.out.grpc.GrpcMemberClientPort;
import io.opentelemetry.context.Context;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

/**
 * 닉네임이 변경되면 발행되는 kafka 메시지를 처리하는 리스너
 * zeroPayload - gRPC 클라이언트를 통해 member 서버에 회원의 닉네임을 요청한다.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NicknameChangeMessageListener {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final GrpcMemberClientPort grpcMemberClientPort;

    /**
     * 유저가 생성되면 이벤트를 수신하고
     * gRPC 클라이언트를 통해 member 서버에 회원 정보를 요청한다.
     *
     * @param record         - Kafka 메시지
     * @param acknowledgment - ack 처리
     * @param partition      - partition
     * @param offset         - offset
     */
    @KafkaListener(
            topics = {"member-nickname-change-outbox"},
            groupId = "social-graph-group-nickname-change",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenNicknameChange(
            ConsumerRecord<String, String> record,
            Acknowledgment acknowledgment,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) throws JsonProcessingException {
        // 1. 이벤트 객체로 변환한다.
        String jsonValue = record.value();
        OutboxEvent event = objectMapper.readValue(jsonValue, NicknameChangeEvent.class);

        // 2. gRPC 요청을 보내고 응답 받기
        MemberProto.MemberNicknameResponse result =
                grpcMemberClientPort.getNicknameById(event.getPayload(), Context.current());
        log.info(String.valueOf(result));

        // 3. ack 처리
        acknowledgment.acknowledge();
    }

}
