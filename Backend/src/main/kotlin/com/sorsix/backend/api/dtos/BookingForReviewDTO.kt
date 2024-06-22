package com.sorsix.backend.api.dtos

import java.time.LocalDate

data class BookingForReviewDTO(
    val id: Long,
    val guest: UserAccountDTO,
    val property: PropertyCardDTO,
    val checkIn: LocalDate,
    val checkOut: LocalDate,
    val nightlyPrice: Double,
    val serviceFee: Double,
    val cleaningFee: Double,
    val status: String,
)
