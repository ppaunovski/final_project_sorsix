package com.sorsix.backend.repository.review_component_repository

import com.sorsix.backend.domain.entities.ReviewComponent
import org.springframework.data.repository.findByIdOrNull

class ReviewComponentRepositoryImpl(
        private val jpaReviewComponentRepository: JpaReviewComponentRepository
) : ReviewComponentRepository{
    override fun findAll(): List<ReviewComponent> =
            this.jpaReviewComponentRepository.findAll()

    override fun findById(id: Long): ReviewComponent? =
            this.jpaReviewComponentRepository.findByIdOrNull(id)

    override fun save(reviewComponent: ReviewComponent): ReviewComponent =
            this.jpaReviewComponentRepository.save(reviewComponent)
}