package com.sorsix.backend.repository.user_review

import com.sorsix.backend.domain.entities.UserReview
import org.springframework.stereotype.Repository

@Repository
class UserReviewRepositoryImpl(private val userReviewRepository: JpaUserReviewRepository): UserReviewRepository{
    override fun findAll(): List<UserReview> {
        return userReviewRepository.findAll()
    }

    override fun findById(id: Long): UserReview? {
        return userReviewRepository.findById(id).orElse(null)
    }

    override fun save(userReview: UserReview): UserReview {
        return userReviewRepository.save(userReview)
    }

    override fun deleteById(id: Long) {
        userReviewRepository.deleteById(id)
    }
}