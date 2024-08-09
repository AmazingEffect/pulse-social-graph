package com.pulse.social_graph.adapter.out.event.outbox;

public interface OutboxEvent {

    Long getPayload();

    String getEventType();

}
