package com.sorsix.backend.repository.user_review_repository

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
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

    override fun findAllByProperty(property: Property): List<UserReview> =
        this.userReviewRepository.findAllByProperty(property)

    override fun hasUserLeftReviewForProperty(guest: UserAccount, property: Property): Boolean =
        this.userReviewRepository.existsByUserAndProperty(guest, property)

    override fun hasUserLeftReviewForPropertyAndBooking(
        guest: UserAccount,
        property: Property,
        booking: Booking
    ): Boolean =
        this.userReviewRepository.existsByUserAndPropertyAndBooking(guest, property, booking)

    override fun hasReviewForBooking(booking: Long, guest: Long): Boolean =
        this.userReviewRepository.existsByUserAndBooking(guest, booking)

}