package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.ReviewDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.domain.entities.UserReview
import com.sorsix.backend.repository.user_review_repository.UserReviewRepository
import org.springframework.stereotype.Service

@Service
class UserReviewService(
    val userReviewRepository: UserReviewRepository,
    private val componentRatingService: ComponentRatingService
) {
    fun findAllUserReviews() = userReviewRepository.findAll()
    fun findUserReviewById(id: Long) = userReviewRepository.findById(id)
    fun saveUserReview(userReview: UserReview) = userReviewRepository.save(userReview)
    fun deleteUserReviewById(id: Long) = userReviewRepository.deleteById(id)


}