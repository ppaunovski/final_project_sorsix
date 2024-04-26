package com.sorsix.backend.repository.user_review_repository

import com.sorsix.backend.domain.entities.UserReview

interface UserReviewRepository {
    fun findAll(): List<UserReview>
    fun findById(id: Long): UserReview?
    fun save(userReview: UserReview): UserReview
    fun deleteById(id: Long)
}