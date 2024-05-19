package com.sorsix.backend.repository.booking_repository

import com.sorsix.backend.api.dtos.BookingDTO
import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BookingRepository {
    fun findAll(): List<Booking>
    fun findById(id: Long): Booking?
    fun save(booking: Booking): Booking
    fun deleteById(id: Long)
    fun findAllByGuest(guest: Long): List<Booking>
    fun hasFinishedBooking(findPropertyById: Property, guest: UserAccount): Boolean
    fun findAllByGuestPagination(guest: UserAccount, pageable: Pageable): Page<Booking>
}