package com.pulse.social_graph.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 친구 추천
 * 추천된 친구를 나타내는 엔티티
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "recommendation")
public class RecommendationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                  // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;            // 회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommended_friend_id")
    private MemberEntity recommendedFriend; // 추천 친구

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecommendationEntity that)) return false;
        return this.id != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
