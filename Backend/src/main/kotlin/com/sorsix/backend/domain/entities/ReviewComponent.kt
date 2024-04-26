package com.sorsix.backend.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "review_component")
data class ReviewComponent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rc_id", nullable = false)
    val id: Long,
    @Column(nullable = false, name = "rc_component_name")
    val rcComponentName: String,
)
