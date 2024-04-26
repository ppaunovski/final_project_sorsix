package com.sorsix.backend.repository.booking_guests_repository

import com.sorsix.backend.domain.entities.BookingGuest

interface BookingGuestsRepository {
    fun findAll(): List<BookingGuest>
    fun findById(id: Long): BookingGuest?
    fun save(bookingGuest: BookingGuest): BookingGuest
}