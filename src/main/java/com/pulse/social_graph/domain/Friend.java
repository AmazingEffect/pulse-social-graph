package com.pulse.social_graph.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 친구 추가/삭제
 * 친구 관계를 나타내는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Friend {

    private Long id;               // PK
    private Member member;         // 사용자
    private Member friend;         // 친구
    private String status;         // pending, accepted, rejected

}
