package com.sorsix.backend.repository.booking_status_repository

import com.sorsix.backend.domain.entities.BookingStatus
import org.springframework.data.repository.findByIdOrNull

class BookingStatusRepositoryImpl(
        private val jpaBookingStatusRepository: JpaBookingStatusRepository
) : BookingStatusRepository {
    override fun findAll(): List<BookingStatus> =
            this.jpaBookingStatusRepository.findAll()

    override fun findById(id: Long): BookingStatus? =
            this.jpaBookingStatusRepository.findByIdOrNull(id)

    override fun save(bookingStatus: BookingStatus): BookingStatus =
            this.jpaBookingStatusRepository.save(bookingStatus)
}