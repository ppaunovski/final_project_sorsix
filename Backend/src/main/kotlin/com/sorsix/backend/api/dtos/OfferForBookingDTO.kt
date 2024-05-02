package com.sorsix.backend.api.dtos

import java.time.LocalDate

data class OfferForBookingDTO(
    val start: LocalDate,
    val end: LocalDate,
    val totalPrice: Double,
    val nightlyPrices: Map<LocalDate, Double>,
    val serviceFee: Double,
    val cleaningFee: Double,
)
