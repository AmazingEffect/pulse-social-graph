package com.pulse.social_graph.adapter.out.persistence.adapter;

import com.pulse.social_graph.adapter.out.persistence.repository.SocialGraphOutboxRepository;
import com.pulse.social_graph.application.port.out.CreateSocialGraphOutboxPort;
import com.pulse.social_graph.application.port.out.FindSocialGraphOutboxPort;
import com.pulse.social_graph.domain.SocialGraphOutbox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SocialGraphOutboxPersistAdapter implements CreateSocialGraphOutboxPort, FindSocialGraphOutboxPort {

    private final SocialGraphOutboxRepository socialGraphOutboxRepository;


    /**
     * @param socialGraphOutbox SocialGraphOutbox
     * @return Long
     * @apiNote OutboxEvent를 저장한다. 상태는 PENDING(대기)으로 저장
     */
    @Override
    public Long saveSocialGraphOutboxEvent(SocialGraphOutbox socialGraphOutbox) {
        return 0L;
    }


    /**
     * @param payload   Long
     * @param eventType String
     * @return SocialGraphOutbox
     * @apiNote OutboxEvent를 처리대기(PENDING)로 변경
     */
    @Override
    public SocialGraphOutbox findMemberOutboxBy(Long payload, String eventType) {
        return null;
    }

}
