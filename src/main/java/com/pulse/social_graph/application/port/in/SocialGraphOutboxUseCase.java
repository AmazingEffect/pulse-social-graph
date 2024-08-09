package com.pulse.social_graph.application.port.in;

import com.pulse.social_graph.adapter.out.event.outbox.OutboxEvent;

public interface SocialGraphOutboxUseCase {

    void markOutboxEventPending(OutboxEvent event);

    Long saveOutboxEvent(OutboxEvent event);

    void markOutboxEventSuccess(OutboxEvent event);

    void markOutboxEventFailed(OutboxEvent event);

    String getKafkaTopic(OutboxEvent event);

    void markOutboxEventProcessed(OutboxEvent event);


}
