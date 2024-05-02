package com.sorsix.backend.api.dtos

import java.time.LocalDate

data class PropertyAvailabilityDTO(
    val startDate: LocalDate,
    val endDate: LocalDate,
)
