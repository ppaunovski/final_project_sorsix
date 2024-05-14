package com.sorsix.backend.api.requests

import java.time.LocalDate

data class OfferRequest(
    val checkInDate: LocalDate,
    val checkOutDate: LocalDate,
    val numberOfAdults: Int,
    val numberOfChildren: Int,
    val numberOfPets: Int,
)
