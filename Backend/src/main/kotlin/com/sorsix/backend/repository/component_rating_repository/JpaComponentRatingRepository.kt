package com.sorsix.backend.repository.component_rating_repository

import com.sorsix.backend.domain.entities.ComponentRating
import com.sorsix.backend.domain.entities.UserReview
import com.sorsix.backend.repository.review_component_repository.JpaReviewComponentRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaComponentRatingRepository: JpaRepository<ComponentRating, Long> {
    fun findAllByUserReview(userReview: UserReview): List<ComponentRating>
    @Query("SELECT AVG(cr.rating) FROM ComponentRating cr WHERE cr.userReview = ?1")
    fun averageRatingByUserReview(userReview: UserReview): Double
}