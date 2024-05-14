package com.sorsix.backend.repository.booking_repository

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.data.jpa.repository.JpaRepository
import java.sql.Date
import java.time.LocalDate

interface JpaBookingRepository: JpaRepository<Booking, Long>{
     fun findAllByGuest(guest: UserAccount): List<Booking>
     fun existsByGuestAndPropertyAndCheckOutBefore(guest: UserAccount, property: Property, checkOut: LocalDate): Boolean
}