package com.sorsix.backend.repository.booking_status_repository

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.BookingStatus

interface BookingStatusRepository {
    fun findAll(): List<BookingStatus>
    fun findById(id: Long): BookingStatus?
    fun save(bookingStatus: BookingStatus): BookingStatus
}