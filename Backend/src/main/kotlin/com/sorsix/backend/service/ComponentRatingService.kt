package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.AverageComponentRatingDTO
import com.sorsix.backend.api.dtos.ComponentRatingDTO
import com.sorsix.backend.api.dtos.ReviewDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.domain.entities.ComponentRating
import com.sorsix.backend.domain.entities.ReviewComponent
import com.sorsix.backend.domain.entities.UserReview
import com.sorsix.backend.repository.component_rating_repository.ComponentRatingRepository
import com.sorsix.backend.repository.review_component_repository.ReviewComponentRepository
import com.sorsix.backend.repository.user_review_repository.UserReviewRepository
import com.sorsix.backend.service.exceptions.UserReviewNotFoundException
import org.springframework.stereotype.Service

@Service
class ComponentRatingService(
    private val componentRatingRepository: ComponentRatingRepository,
    private val reviewRepository: UserReviewRepository,
    private val reviewComponentRepository: ReviewComponentRepository
) {

    fun findAllComponentRatingsForUserReview(userReviewId: Long): List<ComponentRatingDTO> {
        val userReview = this.reviewRepository.findById(userReviewId) ?: throw UserReviewNotFoundException(userReviewId)
        return this.componentRatingRepository.findAllByUserReview(userReview).map { this.mapComponentRatingToDTO(it) }
    }

    fun mapComponentRatingToDTO(componentRating: ComponentRating): ComponentRatingDTO {
        return ComponentRatingDTO(
            id = componentRating.id,
            rating = componentRating.rating,
            reviewComponent = componentRating.reviewComponent,
            userReview = this.mapUserReviewToReviewDTO(componentRating.userReview)
        )
    }

    fun findAverageComponentRatingForUserReview(id: Long): Double {
        val userReview = this.reviewRepository.findById(id) ?: throw UserReviewNotFoundException(id)
        return this.componentRatingRepository.averageRatingByUserReview(userReview)
    }

    fun findAverageComponentRatingAverageForProperty(propertyId: Long): List<AverageComponentRatingDTO> {
        return this.reviewComponentRepository.findAll().map {
            println(it)
            println(this.componentRatingRepository.averageRatingByPropertyAndComponentRating(propertyId, it.id))
            this.componentRatingRepository.averageRatingByPropertyAndComponentRating(propertyId, it.id) ?: AverageComponentRatingDTO(it.rcComponentName, 0.0)

        }
//            .associate { it.name to it.averageRating }
    }

    fun mapUserReviewToReviewDTO(userReview: UserReview): ReviewDTO {
        return ReviewDTO(
            id = userReview.id,
            comment = userReview.comment,
            user = UserAccountDTO(
                id = userReview.user.id,
                email = userReview.user.email,
                firstName = userReview.user.firstName,
                lastName = userReview.user.lastName,
                joinedDate = userReview.user.joinedDate,
                dateHostStarted = userReview.user.dateHostStarted
            ),
            reviewDate = userReview.reviewDate,
            averageRating = this.componentRatingRepository.averageRatingByUserReview(userReview)
        )
    }


}