package com.sorsix.backend.api.requests

data class ReviewRequest(
    val propertyId: Long,
    val bookingId: Long,
    val comment: String,
    val componentRatings: List<ComponentRatingRequest>,
)
