package com.sorsix.backend.api.dtos

import com.sorsix.backend.domain.entities.ReviewComponent

data class ComponentRatingDTO (
    val id: Long,
    val rating: Int,
    val reviewComponent: ReviewComponent,
    val userReview: ReviewDTO
)