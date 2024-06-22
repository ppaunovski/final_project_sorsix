package com.sorsix.backend.api.responses

import com.sorsix.backend.api.dtos.ReviewDTO

data class ReviewsResponse(
    val content: List<ReviewDTO>,
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int,
    val last: Boolean,
)
