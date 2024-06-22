package com.sorsix.backend.repository.guests

import com.sorsix.backend.domain.entities.BookingGuest

interface BookingGuestsRepository {
    fun findAll(): List<BookingGuest>

    fun findById(id: Long): BookingGuest?

    fun save(bookingGuest: BookingGuest): BookingGuest
}
