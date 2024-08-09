package com.pulse.social_graph.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 친구 관계 설정 (친한 친구/지인 등 분류)
 * 친구 관계의 분류를 나타내는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FriendClassification {

    private Long id;                  // PK
    private Member member;            // 회원
    private Member friend;            // 친구
    private String classification;    // 예: 친한 친구, 지인 등

}
