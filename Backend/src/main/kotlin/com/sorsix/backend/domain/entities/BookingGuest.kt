package com.sorsix.backend.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "booking_guests")
data class BookingGuest(
    @Id
    @Column(name = "bg_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "b_id", nullable = false)
    val booking: Booking,
    @Column(name = "bg_num_guests", nullable = false)
    val numGuests: Int,
    @ManyToOne
    @JoinColumn(name = "gt_id", nullable = false)
    val guestType: GuestType
)
