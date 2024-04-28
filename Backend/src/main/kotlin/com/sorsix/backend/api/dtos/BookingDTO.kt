package com.sorsix.backend.api.dtos

import com.sorsix.backend.domain.entities.BookingStatus
import java.time.LocalDate

data class BookingDTO (
    val id: Long,
    val guest: UserAccountDTO,
    val property: PropertyDTO,
    val checkIn: LocalDate,
    val checkOut: LocalDate,
    val nightlyPrice: Double,
    val serviceFee: Double,
    val cleaningFee: Double,
    val status: BookingStatus
)