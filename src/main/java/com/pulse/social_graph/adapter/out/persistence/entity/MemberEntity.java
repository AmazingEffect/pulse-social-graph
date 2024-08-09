package com.pulse.social_graph.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 회원 정보를 일부 가지는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
public class MemberEntity extends BaseEntity {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberEntity that)) return false;
        return that.id != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
