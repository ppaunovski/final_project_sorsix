package com.sorsix.backend.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "booking_status")
data class BookingStatus(
    @Id
    @Column(name = "bs_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "bs_status_name", nullable = false)
    val name: String,
)
