package com.sorsix.backend.api.dtos

import java.time.LocalDateTime

data class ErrorDTO (
    val message: String?,
    val status: Int,
    val error: String?,
    val timestamp: LocalDateTime
)