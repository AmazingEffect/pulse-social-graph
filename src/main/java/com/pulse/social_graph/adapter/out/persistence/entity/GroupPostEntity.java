package com.pulse.social_graph.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 그룹 내 게시글 공유
 * 그룹 내 게시글을 나타내는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "group_post")
public class GroupPostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;               // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private UserGroupEntity userGroupEntity;           // 그룹

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;         // 작성자

    @Column(name = "content", nullable = false)
    private String content;        // 내용

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupPostEntity that)) return false;
        return this.id != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
