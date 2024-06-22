package com.sorsix.backend.repository.users.review

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.domain.entities.UserReview
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserReviewRepository : JpaRepository<UserReview, Long> {
    fun findAllByProperty(property: Property): List<UserReview>

    fun existsByUserAndProperty(
        guest: UserAccount,
        property: Property,
    ): Boolean

    fun existsByUserAndPropertyAndBooking(
        guest: UserAccount,
        property: Property,
        booking: Booking,
    ): Boolean

    fun existsByUserAndBooking(
        guest: UserAccount,
        booking: Booking,
    ): Boolean
}
