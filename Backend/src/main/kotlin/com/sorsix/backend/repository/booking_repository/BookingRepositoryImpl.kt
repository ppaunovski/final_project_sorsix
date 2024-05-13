package com.sorsix.backend.repository.booking_repository

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.stereotype.Repository

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

    override fun findAllByGuest(guest: UserAccount): List<Booking> =
        this.bookingRepository.findAllByGuest(guest)


}