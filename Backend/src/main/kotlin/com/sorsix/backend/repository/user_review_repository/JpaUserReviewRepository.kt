package com.sorsix.backend.repository.user_review_repository

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.domain.entities.UserReview
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaUserReviewRepository: JpaRepository<UserReview, Long>{
    fun findAllByProperty(property: Property): List<UserReview>
    fun findAllByProperty(property: Property, pageable: Pageable): Page<UserReview>
    fun existsByUserAndProperty(guest: UserAccount, property: Property): Boolean
    fun existsByUserAndPropertyAndBooking(guest: UserAccount, property: Property, booking: Booking): Boolean
    fun existsByUserAndBooking(guest: UserAccount, booking: Booking): Boolean
}