package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.ComponentRatingDTO
import com.sorsix.backend.api.dtos.ReviewDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.domain.entities.ComponentRating
import com.sorsix.backend.domain.entities.UserReview
import com.sorsix.backend.repository.component_rating_repository.ComponentRatingRepository
import org.springframework.stereotype.Service

@Service
class ComponentRatingService(
    private val componentRatingRepository: ComponentRatingRepository,
    private val reviewService: ReviewService
) {

    fun findAllComponentRatingsForUserReview(userReviewId: Long): List<ComponentRatingDTO> {
        val userReview = this.reviewService.findById(userReviewId)
        return this.componentRatingRepository.findAllByUserReview(userReview).map { this.mapComponentRatingToDTO(it) }
    }

    fun mapComponentRatingToDTO(componentRating: ComponentRating): ComponentRatingDTO {
        return ComponentRatingDTO(
            id = componentRating.id,
            rating = componentRating.rating,
            reviewComponent = componentRating.reviewComponent,
            userReview = this.reviewService.getReviewDTOById(componentRating.userReview.id)
        )
    }


}