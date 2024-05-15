package com.sorsix.backend.repository.booking_repository

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.sql.Date
import java.time.LocalDate

interface JpaBookingRepository: JpaRepository<Booking, Long>{
     @Query("select * from booking b where b.u_id = :guest order by b.b_booking_date desc", nativeQuery = true)
     fun findAllByGuest(guest: Long): List<Booking>
     fun existsByGuestAndPropertyAndCheckOutBefore(guest: UserAccount, property: Property, checkOut: LocalDate): Boolean
     fun existsByGuestAndPropertyAndCheckInAfter(
          guest: UserAccount,
          findPropertyById: Property,
          now: LocalDate?
     ): Boolean

}