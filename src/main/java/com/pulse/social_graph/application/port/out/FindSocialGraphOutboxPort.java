package com.pulse.social_graph.application.port.out;

import com.pulse.social_graph.domain.SocialGraphOutbox;

public interface FindSocialGraphOutboxPort {

    SocialGraphOutbox findMemberOutboxBy(Long payload, String eventType);

}
