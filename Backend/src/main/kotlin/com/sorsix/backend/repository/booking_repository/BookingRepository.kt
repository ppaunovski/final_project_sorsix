package com.sorsix.backend.repository.booking_repository

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount

interface BookingRepository {
    fun findAll(): List<Booking>
    fun findById(id: Long): Booking?
    fun save(booking: Booking): Booking
    fun deleteById(id: Long)
    fun findAllByGuest(guest: Long): List<Booking>
    fun hasFinishedBooking(findPropertyById: Property, guest: UserAccount): Boolean
}