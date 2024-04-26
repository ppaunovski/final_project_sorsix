package com.sorsix.backend.repository.booking_repository

import com.sorsix.backend.domain.entities.Booking
import org.springframework.data.jpa.repository.JpaRepository

interface JpaBookingRepository: JpaRepository<Booking, Long>{
}