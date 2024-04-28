package com.sorsix.backend.api.dtos

import java.time.LocalDate

data class UserAccountDTO (
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val joinedDate: LocalDate,
    val dateHostStarted: LocalDate?,
)