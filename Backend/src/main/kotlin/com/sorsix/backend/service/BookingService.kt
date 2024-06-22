package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.BookingDTO
import com.sorsix.backend.api.dtos.BookingForReviewDTO
import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.PropertyAvailability
import com.sorsix.backend.domain.enums.BookingStatusEnum
import com.sorsix.backend.repository.booking.BookingRepository
import com.sorsix.backend.repository.booking.status.BookingStatusRepository
import com.sorsix.backend.repository.property.availabilities.PropertyAvailabilityRepository
import com.sorsix.backend.repository.property.images.PropertyImagesRepository
import com.sorsix.backend.repository.users.UserAccountRepository
import com.sorsix.backend.repository.users.review.UserReviewRepository
import com.sorsix.backend.service.exceptions.BookingNotFoundException
import com.sorsix.backend.service.exceptions.BookingStatusNotFoundException
import com.sorsix.backend.service.exceptions.UnauthorizedAccessException
import com.sorsix.backend.service.exceptions.UserAccountNotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
    private val userAccountService: UserAccountService,
    private val imagesRepository: PropertyImagesRepository,
    private val userAccountRepository: UserAccountRepository,
    private val bookingStatusRepository: BookingStatusRepository,
    private val availabilityRepository: PropertyAvailabilityRepository,
    private val reviewRepository: UserReviewRepository,
    private val componentRatingService: ComponentRatingService,
    private val imageRepository: PropertyImagesRepository,
    private val dtoMapperService: ClassToDTOMapperService,
) {
    fun findAllBookings() = bookingRepository.findAll().map { dtoMapperService.mapBookingToDTO(it) }

    fun findBookingById(id: Long) = bookingRepository.findById(id) ?: throw BookingNotFoundException(id)

    fun getBookingDTOById(id: Long) =
        bookingRepository.findById(id)?.let { dtoMapperService.mapBookingToDTO(it) } ?: throw BookingNotFoundException(
            id,
        )

    fun saveBooking(booking: Booking) = bookingRepository.save(booking).let { dtoMapperService.mapBookingToDTO(it) }

    fun deleteBookingById(id: Long) = bookingRepository.deleteById(id)

    fun getBookingsForUser(
        page: Int,
        size: Int,
        authentication: Authentication,
    ): Page<BookingDTO> {
        val pageable = PageRequest.of(page, size)
        val guest = this.userAccountRepository.findByEmail(authentication.name)
        return guest?.let { account ->
            this.bookingRepository.findAllByGuestPagination(account, pageable).map { dtoMapperService.mapBookingToDTO(it) }
        } ?: throw UserAccountNotFoundException("User not found")
    }

    @Transactional
    fun confirmBooking(
        id: Long,
        authentication: Authentication,
    ): BookingDTO {
        val guest = userAccountService.findUserByEmail(authentication.name)

        val booking = bookingRepository.findById(id) ?: throw BookingNotFoundException(id)

        if (booking.guest.id != guest.id) throw UnauthorizedAccessException("User is not the owner of the booking")

        booking.status = this.bookingStatusRepository.findById(BookingStatusEnum.APPROVED.ordinal.toLong())
            ?: throw BookingStatusNotFoundException("Booking status not found")
        return bookingRepository.save(booking).let { dtoMapperService.mapBookingToDTO(it) }
    }

    @Transactional
    fun cancelBooking(
        id: Long,
        authentication: Authentication,
    ): BookingDTO {
        val guest = userAccountService.findUserByEmail(authentication.name)

        val booking = this.findBookingById(id)

        if (booking.guest.id != guest.id && booking.property.host.id != guest.id) {
            throw UnauthorizedAccessException(
                "User is not the owner of the booking",
            )
        }

        if (booking.checkIn <= (LocalDate.now())) throw UnauthorizedAccessException("Booking has already started")

        booking.status = this.bookingStatusRepository.findById(BookingStatusEnum.CANCELLED.ordinal.toLong())
            ?: throw BookingStatusNotFoundException("Booking status not found")

        val first =
            this.availabilityRepository.findAllForProperty(booking.property).first {
                it.endDate == booking.checkIn.minusDays(1)
            }

        val second =
            this.availabilityRepository.findAllForProperty(booking.property).first {
                it.startDate == booking.checkOut.plusDays(1)
            }

        this.availabilityRepository.deleteById(first.id)
        this.availabilityRepository.deleteById(second.id)

        this.availabilityRepository.save(
            PropertyAvailability(
                id = 0,
                property = booking.property,
                startDate = first.startDate,
                endDate = second.endDate,
            ),
        )

        return bookingRepository.save(booking).let { dtoMapperService.mapBookingToDTO(it) }
    }

    fun hasFinishedBooking(
        findPropertyById: Property,
        authentication: Authentication,
    ): Boolean {
        val guest = userAccountService.findUserByEmail(authentication.name)
        return bookingRepository.hasFinishedBooking(findPropertyById, guest)
    }

    fun getBookingForReview(
        id: Long,
        authentication: Authentication?,
    ): BookingForReviewDTO {
        if (authentication == null) throw UnauthorizedAccessException("User is not authenticated")

        val guest = userAccountService.findUserByEmail(authentication.name)

        val booking = this.findBookingById(id)

        if (booking.status != (
                this.bookingStatusRepository.findById(BookingStatusEnum.APPROVED.ordinal.toLong())
                    ?: { BookingStatusNotFoundException("Booking status not found") }
            )
        ) {
            throw UnauthorizedAccessException("Booking has not been approved")
        }

        if (booking.checkIn > LocalDate.now()) throw UnauthorizedAccessException("Booking has not started yet")

        if (this.reviewRepository.hasReviewForBooking(
                booking,
                guest,
            )
        ) {
            throw UnauthorizedAccessException("User has already reviewed this booking")
        }

        return BookingForReviewDTO(
            id = booking.id,
            guest = this.dtoMapperService.mapUserAccountToDTO(booking.guest),
            property = dtoMapperService.mapPropertyToPropertyCardDTO(booking.property),
            checkIn = booking.checkIn,
            checkOut = booking.checkOut,
            nightlyPrice = booking.nightlyPrice,
            serviceFee = booking.serviceFee,
            cleaningFee = booking.cleaningFee,
            status = booking.status.name,
        )
    }
}
