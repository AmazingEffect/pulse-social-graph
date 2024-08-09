package com.pulse.social_graph.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 사용자 그룹 생성/그룹 내 게시글 공유
 * 사용자 그룹을 나타내는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_group")
public class UserGroupEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;               // PK

    @Column(name = "name", unique = true, nullable = false)
    private String name;           // 그룹 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private MemberEntity owner;          // 그룹 생성자

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGroupEntity that)) return false;
        return this.id != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
