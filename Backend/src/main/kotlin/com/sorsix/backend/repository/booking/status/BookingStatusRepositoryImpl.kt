package com.sorsix.backend.repository.booking.status

import com.sorsix.backend.domain.entities.BookingStatus
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class BookingStatusRepositoryImpl(
    private val jpaBookingStatusRepository: JpaBookingStatusRepository,
) : BookingStatusRepository {
    override fun findAll(): List<BookingStatus> = this.jpaBookingStatusRepository.findAll()

    override fun findById(id: Long): BookingStatus? = this.jpaBookingStatusRepository.findByIdOrNull(id)

    override fun save(bookingStatus: BookingStatus): BookingStatus = this.jpaBookingStatusRepository.save(bookingStatus)
}
