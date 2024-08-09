package com.pulse.social_graph.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 그룹 내 게시글 공유
 * 그룹 내 게시글을 나타내는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GroupPost {

    private Long id;               // PK
    private UserGroup userGroup;           // 그룹
    private Member member;         // 작성자
    private String content;        // 내용

}
