package com.sorsix.backend.repository.guests

import com.sorsix.backend.domain.entities.BookingGuest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class BookingGuestRepositoryImpl(
    private val jpaBookingGuestRepository: JpaBookingGuestRepository,
) : BookingGuestsRepository {
    override fun findAll(): List<BookingGuest> = this.jpaBookingGuestRepository.findAll()

    override fun findById(id: Long): BookingGuest? = this.jpaBookingGuestRepository.findByIdOrNull(id)

    override fun save(bookingGuest: BookingGuest): BookingGuest = this.jpaBookingGuestRepository.save(bookingGuest)
}
