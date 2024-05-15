package com.sorsix.backend.repository.booking_repository

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class BookingRepositoryImpl(private val bookingRepository: JpaBookingRepository): BookingRepository{
    override fun findAll(): List<Booking> =
         bookingRepository.findAll()


    override fun findById(id: Long): Booking? =
         bookingRepository.findById(id).orElse(null)


    override fun save(booking: Booking): Booking =
         bookingRepository.save(booking)


    override fun deleteById(id: Long) =
        bookingRepository.deleteById(id)

    override fun findAllByGuest(guest: Long): List<Booking> =
        this.bookingRepository.findAllByGuest(guest)

    override fun hasFinishedBooking(findPropertyById: Property, guest: UserAccount): Boolean =
        this.bookingRepository.existsByGuestAndPropertyAndCheckInAfter(guest, findPropertyById, LocalDate.now())



}