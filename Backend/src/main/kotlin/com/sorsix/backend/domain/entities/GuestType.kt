package com.sorsix.backend.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "guest_type")
data class GuestType(
    @Id
    @Column(name = "gt_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "gt_type_name", nullable = false)
    val name: String
)
