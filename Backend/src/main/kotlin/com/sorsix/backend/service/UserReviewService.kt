package com.sorsix.backend.service

import com.sorsix.backend.domain.entities.UserReview
import com.sorsix.backend.repository.user_review_repository.UserReviewRepository
import org.springframework.stereotype.Service

@Service
class UserReviewService(val userReviewRepository: UserReviewRepository) {
    fun findAllUserReviews() = userReviewRepository.findAll()
    fun findUserReviewById(id: Long) = userReviewRepository.findById(id)
    fun saveUserReview(userReview: UserReview) = userReviewRepository.save(userReview)
    fun deleteUserReviewById(id: Long) = userReviewRepository.deleteById(id)
}