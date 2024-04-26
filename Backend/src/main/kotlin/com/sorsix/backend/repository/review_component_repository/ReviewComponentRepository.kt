package com.sorsix.backend.repository.review_component_repository

import com.sorsix.backend.domain.entities.PropertyType
import com.sorsix.backend.domain.entities.ReviewComponent

interface ReviewComponentRepository {
    fun findAll(): List<ReviewComponent>
    fun findById(id: Long): ReviewComponent?
    fun save(reviewComponent: ReviewComponent): ReviewComponent
}