package com.pulse.social_graph.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 그룹 멤버 관리 (map)
 * 그룹과 사용자 간의 관계를 나타내는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserGroupMember {

    private Long id;               // PK
    private UserGroup userGroup;           // 그룹
    private Member member;         // 사용자
    private String role;           // admin, manager, member
    private LocalDateTime joinedAt; // 가입일

}
