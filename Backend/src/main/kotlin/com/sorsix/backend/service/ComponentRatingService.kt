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
    private val reviewComponentRepository: ReviewComponentRepository,
    private val dtoMapperService: ClassToDTOMapperService
) {

    fun findAllComponentRatingsForUserReview(userReviewId: Long): List<ComponentRatingDTO> {
        val userReview = this.reviewRepository.findById(userReviewId) ?: throw UserReviewNotFoundException("Review with id $userReviewId not found")
        return this.componentRatingRepository.findAllByUserReview(userReview).map { dtoMapperService.mapComponentRatingToDTO(it) }
    }



    fun findAverageComponentRatingForUserReview(id: Long): Double {
        val userReview = this.reviewRepository.findById(id) ?: throw UserReviewNotFoundException("Review with id $id not found")
        return this.componentRatingRepository.averageRatingByUserReview(userReview)
    }

    fun findAverageComponentRatingAverageForProperty(propertyId: Long): List<AverageComponentRatingDTO> {
        return this.reviewComponentRepository.findAll().map {
            println(it)
            println(this.componentRatingRepository.averageRatingByPropertyAndComponentRating(propertyId, it.id))
            this.componentRatingRepository.averageRatingByPropertyAndComponentRating(propertyId, it.id) ?: AverageComponentRatingDTO(it.rcComponentName, 0.0)

        }
    }




}