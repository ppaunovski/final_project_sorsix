package com.sorsix.backend.api.dtos

import java.time.LocalDate

data class ReviewDTO (
    val id: Long,
    val user: UserAccountDTO,
    val comment: String,
    val reviewDate: LocalDate,
    val averageRating: Double
)