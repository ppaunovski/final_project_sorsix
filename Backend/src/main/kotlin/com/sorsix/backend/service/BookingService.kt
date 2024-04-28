package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.BookingDTO
import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.repository.booking_repository.BookingRepository
import com.sorsix.backend.service.exceptions.BookingNotFoundException
import org.springframework.stereotype.Service

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
    private val userAccountService: UserAccountService,
    private val propertyService: PropertyService
) {
    fun findAllBookings() =
        bookingRepository.findAll().map { this.mapBookingToDTO(it) }

    fun findBookingById(id: Long) = bookingRepository.findById(id) ?: throw BookingNotFoundException(id)

    fun getBookingDTOById(id: Long) =
        bookingRepository.findById(id)?.let { this.mapBookingToDTO(it) } ?: throw BookingNotFoundException(id)

    fun saveBooking(booking: Booking) = bookingRepository.save(booking)
    fun deleteBookingById(id: Long) = bookingRepository.deleteById(id)

    fun mapBookingToDTO(booking: Booking): BookingDTO {
        return BookingDTO(
            id = booking.id,
            guest = this.userAccountService.mapUserAccountToDTO(booking.guest),
            property = this.propertyService.mapPropertyToDTO(booking.property),
            checkIn= booking.checkIn,
            checkOut = booking.checkOut,
            nightlyPrice = booking.nightlyPrice,
            serviceFee = booking.serviceFee,
            cleaningFee = booking.cleaningFee,
            status = booking.status
        )
    }
}