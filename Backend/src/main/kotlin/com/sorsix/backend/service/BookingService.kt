package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.BookingDTO
import com.sorsix.backend.api.dtos.PropertyDTO
import com.sorsix.backend.api.dtos.PropertyImageDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.repository.booking_repository.BookingRepository
import com.sorsix.backend.repository.property_images_repository.PropertyImagesRepository
import com.sorsix.backend.service.exceptions.BookingNotFoundException
import org.springframework.stereotype.Service

@Service
class BookingService(
    private val bookingRepository: BookingRepository,
    private val userAccountService: UserAccountService,
    private val imagesRepository: PropertyImagesRepository
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

}