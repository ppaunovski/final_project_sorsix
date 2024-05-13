package com.sorsix.backend.repository.booking_repository

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.data.jpa.repository.JpaRepository

interface JpaBookingRepository: JpaRepository<Booking, Long>{
     fun findAllByGuest(guest: UserAccount): List<Booking>
}