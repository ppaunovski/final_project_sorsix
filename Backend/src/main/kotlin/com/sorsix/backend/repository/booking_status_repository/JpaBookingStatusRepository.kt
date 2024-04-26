package com.sorsix.backend.repository.booking_status_repository

import com.sorsix.backend.domain.entities.BookingStatus
import org.springframework.data.jpa.repository.JpaRepository

interface JpaBookingStatusRepository: JpaRepository<BookingStatus, Long> {
}