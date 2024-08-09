package com.pulse.social_graph.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 팔로우 추가/삭제
 * 팔로우 관계를 나타내는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Follow {

    private Long id;               // PK
    private Member follower;       // 팔로워
    private Member followee;       // 팔로이

}
