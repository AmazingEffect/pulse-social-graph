package com.pulse.social_graph.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 친구 추천
 * 추천된 친구를 나타내는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Recommendation {

    private Long id;                  // PK
    private Member member;            // 회원
    private Member recommendedFriend; // 추천 친구

}
