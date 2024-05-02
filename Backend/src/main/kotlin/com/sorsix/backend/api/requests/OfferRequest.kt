package com.sorsix.backend.api.requests

import java.time.LocalDate

data class OfferRequest(
    val start: LocalDate,
    val end: LocalDate,
)
