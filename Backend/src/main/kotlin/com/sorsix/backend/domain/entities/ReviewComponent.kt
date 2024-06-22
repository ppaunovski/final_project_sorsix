package com.sorsix.backend.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "review_component")
data class ReviewComponent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rc_id", nullable = false)
    val id: Long,
    @Column(nullable = false, name = "rc_component_name")
    val rcComponentName: String,
    @Column(nullable = false, name = "rc_icon")
    val icon: String,
)
