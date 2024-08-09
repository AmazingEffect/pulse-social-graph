package com.pulse.social_graph.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 그룹 생성/그룹 내 게시글 공유
 * 사용자 그룹을 나타내는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserGroup {

    private Long id;               // PK
    private String name;           // 그룹 이름
    private Member owner;          // 그룹 생성자

}
