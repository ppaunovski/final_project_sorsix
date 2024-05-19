package com.sorsix.backend.api.dtos

import jakarta.persistence.Column
import java.math.BigDecimal

data class ProfitsPerPropertyDTO (
    @Column(name = "p_id")
    val propertyId: Long,
    @Column(name = "p_property_name")
    val propertyName: String,
    @Column(name = "sum")
    val totalProfit: Double,
    val totalGuests: Long
)