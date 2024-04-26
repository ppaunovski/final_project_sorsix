package com.sorsix.backend.service

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.repository.booking_repository.BookingRepository
import com.sorsix.backend.service.exceptions.BookingNotFoundException
import org.springframework.stereotype.Service

@Service
class BookingService(val bookingRepository: BookingRepository) {
    fun findAllBookings() = bookingRepository.findAll()
    fun findBookingById(id: Long) = bookingRepository.findById(id) ?: throw BookingNotFoundException(id)
    fun saveBooking(booking: Booking) = bookingRepository.save(booking)
    fun deleteBookingById(id: Long) = bookingRepository.deleteById(id)
}