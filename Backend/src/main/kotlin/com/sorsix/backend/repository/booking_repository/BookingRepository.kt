package com.sorsix.backend.repository.booking_repository

import com.sorsix.backend.domain.entities.Booking

interface BookingRepository {
    fun findAll(): List<Booking>
    fun findById(id: Long): Booking?
    fun save(booking: Booking): Booking
    fun deleteById(id: Long)
}