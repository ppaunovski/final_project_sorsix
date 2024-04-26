package com.sorsix.backend.repository.component_rating_repository

import com.sorsix.backend.domain.entities.ComponentRating
import com.sorsix.backend.repository.review_component_repository.JpaReviewComponentRepository
import org.springframework.data.jpa.repository.JpaRepository

interface JpaComponentRatingRepository: JpaRepository<ComponentRating, Long> {
}