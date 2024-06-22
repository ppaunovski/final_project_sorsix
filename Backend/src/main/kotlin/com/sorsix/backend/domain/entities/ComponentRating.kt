package com.sorsix.backend.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

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
    val reviewComponent: ReviewComponent,
)
