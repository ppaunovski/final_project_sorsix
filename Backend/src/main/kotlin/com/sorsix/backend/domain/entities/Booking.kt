package com.sorsix.backend.domain.entities

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "booking")
data class Booking(
    @Id
    @Column(name = "b_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "u_id", nullable = false)
    val guest: UserAccount,
    @ManyToOne
    @JoinColumn(name = "p_id", nullable = false)
    val property: Property,
    @Column(name = "b_booking_date", nullable = false)
    val bookingDate: LocalDate,
    @Column(name = "b_checkin_date", nullable = false)
    val checkIn: LocalDate,
    @Column(name = "b_checkout_date", nullable = false)
    val checkOut: LocalDate,
    @Column(name = "b_nightly_price", nullable = false)
    val nightlyPrice: Double,
    @Column(name = "b_service_fee", nullable = false)
    val serviceFee: Double,
    @Column(name = "b_cleaning_fee", nullable = false)
    val cleaningFee: Double,
    @ManyToOne
    @JoinColumn(name = "bs_id", nullable = false)
    var status: BookingStatus,
)
