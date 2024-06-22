package com.sorsix.backend.api.requests

data class ComponentRatingRequest(
    val rating: Int,
    val reviewComponentId: Long,
    val reviewComponentName: String,
)
