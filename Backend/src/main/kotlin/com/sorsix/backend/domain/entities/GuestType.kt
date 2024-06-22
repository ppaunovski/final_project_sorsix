package com.sorsix.backend.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "guest_type")
data class GuestType(
    @Id
    @Column(name = "gt_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "gt_type_name", nullable = false)
    val name: String,
    @Column(name = "gt_description", nullable = false)
    val description: String,
)
