package com.sorsix.backend.repository.user_review_repository

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.domain.entities.UserReview
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserReviewRepository {
    fun findAll(): List<UserReview>
    fun findById(id: Long): UserReview?
    fun save(userReview: UserReview): UserReview
    fun deleteById(id: Long)
    fun findAllByProperty(property: Property): List<UserReview>
    fun hasUserLeftReviewForProperty(guest: UserAccount, property: Property): Boolean
    fun hasUserLeftReviewForPropertyAndBooking(guest: UserAccount, property: Property, booking: Booking): Boolean
    fun hasReviewForBooking(booking: Booking, guest: UserAccount): Boolean
    fun findAllByPropertyPagination(property: Property, pageable: Pageable): Page<UserReview>
}