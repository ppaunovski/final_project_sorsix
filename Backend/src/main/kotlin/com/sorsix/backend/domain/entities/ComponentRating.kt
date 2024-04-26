package com.sorsix.backend.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "component_rating")
data class ComponentRating(
    @Id
    @Column(name = "cr_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "cr_rating", nullable = false)
    val rating: Int,
    @ManyToOne
    @JoinColumn(name = "ur_id", nullable = false)
    val userReview: UserReview,
    @ManyToOne
    @JoinColumn(name = "rc_id", nullable = false)
    val reviewComponent: ReviewComponent
)
