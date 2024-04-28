package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.ComponentRatingDTO
import com.sorsix.backend.api.dtos.ReviewDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.domain.entities.UserReview
import com.sorsix.backend.repository.user_review_repository.UserReviewRepository
import com.sorsix.backend.service.exceptions.UserReviewNotFoundException
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val reviewRepository: UserReviewRepository,
    private val propertyService: PropertyService,
) {
    fun getAllReviewsForProperty(id: Long): List<ReviewDTO> {
        val property = this.propertyService.findPropertyById(id)

        return this.reviewRepository.findAllByProperty(property).map { this.mapUserReviewToReviewDTO(it) }
    }

    fun findById(id: Long): UserReview  =
        this.reviewRepository.findById(id) ?: throw UserReviewNotFoundException(id)

    fun getReviewDTOById(id: Long): ReviewDTO {
        return this.reviewRepository.findById(id)?.let {
            this.mapUserReviewToReviewDTO(it)
        } ?: throw UserReviewNotFoundException(id)
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
            reviewDate = userReview.reviewDate
        )
    }
}