package com.pulse.social_graph.repository;

import com.pulse.social_graph.entity.SocialGraphOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OutboxRepository extends JpaRepository<SocialGraphOutbox, Long> {

    Optional<SocialGraphOutbox> findByPayloadAndEventType(Long id, String eventType);

}
