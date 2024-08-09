package com.pulse.social_graph.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 정보를 일부 가지는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

    private Long id;               // PK
    private String email;          // 이메일
    private String name;           // 이름
    private String nickname;       // 닉네임
    private String profilePictureUrl; // 프로필 사진
    private String statusMessage;  // 상태 메시지

}
