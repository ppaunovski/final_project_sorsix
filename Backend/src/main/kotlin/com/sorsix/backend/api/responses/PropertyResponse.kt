package com.sorsix.backend.api.responses

import com.sorsix.backend.api.dtos.PropertyCardDTO

data class PropertyResponse(
    val content: List<PropertyCardDTO>,
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int,
    val last: Boolean,
)
