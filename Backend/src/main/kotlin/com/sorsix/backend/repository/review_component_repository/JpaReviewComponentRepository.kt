package com.sorsix.backend.repository.review_component_repository

import com.sorsix.backend.domain.entities.ReviewComponent
import org.springframework.data.jpa.repository.JpaRepository

interface JpaReviewComponentRepository: JpaRepository<ReviewComponent, Long> {
}