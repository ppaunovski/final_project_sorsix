package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.BookingDTO
import com.sorsix.backend.api.dtos.PropertyDTO
import com.sorsix.backend.api.dtos.PropertyImageDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.BookingStatus
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.PropertyAvailability
import com.sorsix.backend.domain.enums.BookingStatusEnum
import com.sorsix.backend.repository.booking_repository.BookingRepository
import com.sorsix.backend.repository.booking_status_repository.BookingStatusRepository
import com.sorsix.backend.repository.property_availabilities_repository.PropertyAvailabilityRepository
import com.sorsix.backend.repository.property_images_repository.PropertyImagesRepository
import com.sorsix.backend.repository.user_account_repository.UserAccountRepository
import com.sorsix.backend.service.exceptions.BookingNotFoundException
import jakarta.transaction.Transactional
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
    private val userAccountService: UserAccountService,
    private val imagesRepository: PropertyImagesRepository,
    private val userAccountRepository: UserAccountRepository,
    private val bookingStatusRepository: BookingStatusRepository,
    private val availabilityRepository: PropertyAvailabilityRepository
) {
    fun findAllBookings() =
        bookingRepository.findAll().map { this.mapBookingToDTO(it) }

    fun findBookingById(id: Long) = bookingRepository.findById(id) ?: throw BookingNotFoundException(id)

    fun getBookingDTOById(id: Long) =
        bookingRepository.findById(id)?.let { this.mapBookingToDTO(it) } ?: throw BookingNotFoundException(id)

    fun saveBooking(booking: Booking) = bookingRepository.save(booking).let { this.mapBookingToDTO(it) }
    fun deleteBookingById(id: Long) = bookingRepository.deleteById(id)

    fun mapBookingToDTO(booking: Booking): BookingDTO {
        return BookingDTO(
            id = booking.id,
            guest = this.userAccountService.mapUserAccountToDTO(booking.guest),
            property = this.mapPropertyToDTO(booking.property),
            checkIn= booking.checkIn,
            checkOut = booking.checkOut,
            nightlyPrice = booking.nightlyPrice,
            serviceFee = booking.serviceFee,
            cleaningFee = booking.cleaningFee,
            status = booking.status.name
        )
    }

    fun mapPropertyToDTO(property: Property): PropertyDTO {
        return PropertyDTO(
            id = property.id,
            name = property.name,
            description = property.description,
            nightlyPrice = property.nightlyPrice,
            address = property.address,
            guests = property.guests,
            beds = property.beds,
            bedrooms = property.bedrooms,
            bathrooms = property.bathrooms,
            isGuestFavorite = property.isGuestFavorite,
            longitude = property.longitude,
            latitude = property.latitude,
            host = UserAccountDTO(
                id = property.host.id,
                email = property.host.email,
                firstName = property.host.firstName,
                lastName = property.host.lastName,
                joinedDate = property.host.joinedDate,
                dateHostStarted = property.host.dateHostStarted,
            ),
            city = property.city,
            propertyType = property.propertyType,
            images = this.imagesRepository.findAllByPropertyId(property.id).map {
                PropertyImageDTO(
                    id = it.id,
                    propertyId = it.property.id,
                    order = it.order,
                    imageByteArray = it.image,
                    type = it.type
                )
            }
        )
    }

    fun getBookingsForUser(authorizationHeader: String, authentication: Authentication): List<BookingDTO> {
        val guest = this.userAccountRepository.findByEmail(authentication.name)
        return guest?.let { account -> this.bookingRepository.findAllByGuest(account).map { this.mapBookingToDTO(it) } } ?: throw RuntimeException("User not found")
    }

    @Transactional
    fun confirmBooking(id: Long, authentication: Authentication): BookingDTO {
        val guest = userAccountService.findUserByEmail(authentication.name)

        val booking = bookingRepository.findById(id) ?: throw BookingNotFoundException(id)

        if(booking.guest.id != guest.id) throw RuntimeException("User is not the owner of the booking")

        booking.status = this.bookingStatusRepository.findById(BookingStatusEnum.APPROVED.ordinal.toLong()) ?: throw RuntimeException("Booking status not found")
        return bookingRepository.save(booking).let { this.mapBookingToDTO(it) }
    }

    @Transactional
    fun cancelBooking(id: Long, authentication: Authentication): BookingDTO {
        val guest = userAccountService.findUserByEmail(authentication.name)

        val booking = bookingRepository.findById(id) ?: throw BookingNotFoundException(id)

        if(booking.guest.id != guest.id || booking.property.host.id != guest.id) throw RuntimeException("User is not the owner of the booking")

        booking.status = this.bookingStatusRepository.findById(BookingStatusEnum.CANCELLED.ordinal.toLong()) ?: throw RuntimeException("Booking status not found")

        val first = this.availabilityRepository.findAllForProperty(booking.property).first {
            it.endDate == booking.checkIn.minusDays(1)
        }

        val second = this.availabilityRepository.findAllForProperty(booking.property).first {
            it.startDate == booking.checkOut.plusDays(1)
        }

        this.availabilityRepository.deleteById(first.id)
        this.availabilityRepository.deleteById(second.id)

        this.availabilityRepository.save(PropertyAvailability(
            id = 0,
            property = booking.property,
            startDate = first.startDate,
            endDate = second.endDate
        ))

        return bookingRepository.save(booking).let { this.mapBookingToDTO(it) }
    }

}