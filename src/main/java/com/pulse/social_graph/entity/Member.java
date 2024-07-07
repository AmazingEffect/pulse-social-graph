package com.pulse.social_graph.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;               // PK

    @Column(unique = true, nullable = false)
    private String email;          // 이메일

    @Column(name = "name")
    private String name;           // 이름

    @Column(name = "nickname")
    private String nickname;       // 닉네임

    @Column(name = "profile_picture_url")
    private String profilePictureUrl; // 프로필 사진

    @Column(name = "status_message")
    private String statusMessage;  // 상태 메시지

}
