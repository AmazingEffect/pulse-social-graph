package com.pulse.social_graph.adapter.out.persistence.repository;

import com.pulse.social_graph.adapter.out.persistence.entity.SocialGraphOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialGraphOutboxRepository extends JpaRepository<SocialGraphOutboxEntity, Long> {

    Optional<SocialGraphOutboxEntity> findByPayloadAndEventType(Long id, String eventType);

}
