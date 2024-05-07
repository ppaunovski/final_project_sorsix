package com.sorsix.backend.repository.component_rating_repository

import com.sorsix.backend.api.dtos.AverageComponentRatingDTO
import com.sorsix.backend.domain.entities.ComponentRating
import com.sorsix.backend.domain.entities.UserReview

interface ComponentRatingRepository {
    fun findAll(): List<ComponentRating>
    fun findById(id: Long): ComponentRating?
    fun save(componentRating: ComponentRating): ComponentRating
    fun findAllByUserReview(userReview: UserReview): List<ComponentRating>
    fun averageRatingByUserReview(userReview: UserReview): Double
    fun averageRatingByPropertyAndComponentRating(propertyId: Long, componentRating: Long): AverageComponentRatingDTO?
}