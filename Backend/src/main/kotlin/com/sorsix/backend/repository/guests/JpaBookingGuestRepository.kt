package com.sorsix.backend.repository.guests

import com.sorsix.backend.domain.entities.BookingGuest
import org.springframework.data.jpa.repository.JpaRepository

interface JpaBookingGuestRepository : JpaRepository<BookingGuest, Long>
