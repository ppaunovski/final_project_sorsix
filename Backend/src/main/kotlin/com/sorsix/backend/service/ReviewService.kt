package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.ComponentRatingDTO
import com.sorsix.backend.api.dtos.ReviewDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.api.requests.ReviewRequest
import com.sorsix.backend.domain.entities.ComponentRating
import com.sorsix.backend.domain.entities.UserReview
import com.sorsix.backend.repository.booking_repository.BookingRepository
import com.sorsix.backend.repository.component_rating_repository.ComponentRatingRepository
import com.sorsix.backend.repository.review_component_repository.ReviewComponentRepository
import com.sorsix.backend.repository.user_account_repository.UserAccountRepository
import com.sorsix.backend.repository.user_review_repository.UserReviewRepository
import com.sorsix.backend.service.exceptions.ReviewComponentNotFound
import com.sorsix.backend.service.exceptions.UnauthorizedAccessException
import com.sorsix.backend.service.exceptions.UserReviewNotFoundException
import jakarta.transaction.Transactional
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ReviewService(
    private val reviewRepository: UserReviewRepository,
    private val propertyService: PropertyService,
    private val componentRatingRepository: ComponentRatingRepository,
    private val userAccountRepository: UserAccountRepository,
    private val reviewComponentRepository: ReviewComponentRepository,
    private val bookingRepository: BookingRepository,
    private val dtoMapperService: ClassToDTOMapperService
) {
    fun getAllReviewsForProperty(id: Long): List<ReviewDTO> {
        val property = this.propertyService.findPropertyById(id)

        return this.reviewRepository.findAllByProperty(property).map { dtoMapperService.mapUserReviewToReviewDTO(it) }
    }

    fun findById(id: Long): UserReview =
        this.reviewRepository.findById(id) ?: throw UserReviewNotFoundException("Review with id $id not found")

    fun getReviewDTOById(id: Long): ReviewDTO {
        return this.findById(id).let {
            dtoMapperService.mapUserReviewToReviewDTO(it)
        }
    }



    @Transactional
    fun saveReview(review: ReviewRequest, authentication: Authentication?): ReviewDTO {
        if (authentication == null) throw UnauthorizedAccessException("User must be authenticated to leave a review")

        val guest = this.userAccountRepository.findByEmail(authentication.name)
            ?: throw UnauthorizedAccessException("User must be authenticated to leave a review")

        val property = this.propertyService.findPropertyById(review.propertyId)

        if (guest.id == property.host.id)
            throw UnauthorizedAccessException("Hosts cannot leave reviews for their own properties")

        val booking = this.bookingRepository
                .findById(review.bookingId) ?: throw UserReviewNotFoundException("Booking with id ${review.bookingId} not found")

        if (this.reviewRepository.hasUserLeftReviewForPropertyAndBooking(guest, property, booking))
            throw UnauthorizedAccessException("User has already left a review for this booking")

        val userReview = this.reviewRepository.save(
            UserReview(
                comment = review.comment,
                user = guest,
                property = property,
                id = 0,
                reviewDate = LocalDate.now(),
                booking = booking
            )
        )

        review.componentRatings.forEach {
            this.componentRatingRepository.save(
                ComponentRating(
                    id = 0,
                    userReview = userReview,
                    reviewComponent = this.reviewComponentRepository.findById(it.reviewComponentId) ?: throw ReviewComponentNotFound("Review component with id ${it.reviewComponentId} not found"),
                    rating = it.rating
                )
            )
        }

        return dtoMapperService.mapUserReviewToReviewDTO(userReview)
    }
}