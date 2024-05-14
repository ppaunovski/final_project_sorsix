package com.sorsix.backend.service

import com.sorsix.backend.domain.entities.ReviewComponent
import com.sorsix.backend.repository.review_component_repository.ReviewComponentRepository
import org.springframework.stereotype.Service

@Service
class ReviewComponentService(
    private val reviewComponentRepository: ReviewComponentRepository
) {
    fun getAllReviewComponents(): List<ReviewComponent> {
        return this.reviewComponentRepository.findAll()
    }


}
