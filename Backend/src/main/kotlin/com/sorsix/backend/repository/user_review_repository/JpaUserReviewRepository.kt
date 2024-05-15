package com.sorsix.backend.repository.user_review_repository

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.domain.entities.UserReview
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaUserReviewRepository: JpaRepository<UserReview, Long>{
    fun findAllByProperty(property: Property): List<UserReview>
    fun existsByUserAndProperty(guest: UserAccount, property: Property): Boolean
    fun existsByUserAndPropertyAndBooking(guest: UserAccount, property: Property, booking: Booking): Boolean
    @Query("select count(ur.u_id || '-'||ur.p_id||'-'||ur.ur_id||'-'||ur.b_id||'-'||ur.ur_comment||'-'||ur.ur_review_date) > 0 from user_review ur where ur.u_id = :guest and ur.b_id = :booking", nativeQuery = true)
    fun existsByUserAndBooking(guest: Long, booking: Long): Boolean
}