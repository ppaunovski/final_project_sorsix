package com.sorsix.backend.repository.review.component

import com.sorsix.backend.domain.entities.ReviewComponent

interface ReviewComponentRepository {
    fun findAll(): List<ReviewComponent>

    fun findById(id: Long): ReviewComponent?

    fun save(reviewComponent: ReviewComponent): ReviewComponent
}
