package com.sorsix.backend.repository.user_review_repository

import com.sorsix.backend.domain.entities.UserReview
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserReviewRepository: JpaRepository<UserReview, Long>{
}