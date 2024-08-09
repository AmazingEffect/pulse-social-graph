package com.pulse.social_graph.application.port.out;

import com.pulse.social_graph.domain.SocialGraphOutbox;

public interface CreateSocialGraphOutboxPort {

    Long saveSocialGraphOutboxEvent(SocialGraphOutbox socialGraphOutbox);

}
