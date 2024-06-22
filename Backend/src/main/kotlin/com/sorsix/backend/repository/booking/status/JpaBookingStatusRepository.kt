package com.sorsix.backend.repository.booking.status

import com.sorsix.backend.domain.entities.BookingStatus
import org.springframework.data.jpa.repository.JpaRepository

interface JpaBookingStatusRepository : JpaRepository<BookingStatus, Long>
