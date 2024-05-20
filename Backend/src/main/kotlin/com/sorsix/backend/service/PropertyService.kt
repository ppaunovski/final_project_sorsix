package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.*
import com.sorsix.backend.api.requests.OfferRequest
import com.sorsix.backend.api.requests.PropertyImageRequest
import com.sorsix.backend.api.requests.PropertyRequest
import com.sorsix.backend.api.responses.PropertyResponse
import com.sorsix.backend.domain.entities.*
import com.sorsix.backend.domain.enums.BookingStatusEnum
import com.sorsix.backend.domain.enums.GuestTypeEnum
import com.sorsix.backend.repository.booking_guests_repository.BookingGuestsRepository
import com.sorsix.backend.repository.booking_repository.BookingRepository
import com.sorsix.backend.repository.booking_repository.BookingRepositoryImpl
import com.sorsix.backend.repository.booking_status_repository.BookingStatusRepository
import com.sorsix.backend.repository.guest_type_repository.GuestTypeRepository
import com.sorsix.backend.repository.property_availabilities_repository.PropertyAvailabilityRepository
import com.sorsix.backend.repository.property_images_repository.PropertyImagesRepository
import com.sorsix.backend.repository.property_repository.PropertyRepository
import com.sorsix.backend.repository.user_review_repository.UserReviewRepository
import com.sorsix.backend.service.exceptions.PropertyCapacityException
import com.sorsix.backend.service.exceptions.PropertyNotAvailableException
import com.sorsix.backend.service.exceptions.PropertyNotFoundException
import com.sorsix.backend.service.exceptions.UnauthorizedAccessException
import jakarta.transaction.Transactional
import org.springframework.beans.propertyeditors.URIEditor
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.net.URI
import java.net.URLDecoder
import java.time.LocalDate

@Service
class PropertyService(
    private val propertyRepository: PropertyRepository,
    private val propertyAvailabilityRepository: PropertyAvailabilityRepository,
    private val bookingService: BookingService,
    private val userService: UserAccountService,
    private val bookingStatusRepository: BookingStatusRepository,
    private val imagesService: PropertyImagesService,
    private val bookingGuestsRepository: BookingGuestsRepository,
    private val guestTypeRepository: GuestTypeRepository,
    private val bookingRepository: BookingRepository,
    private val dtoMapperService: ClassToDTOMapperService,
    private val propertyAttributesService: PropertyAttributesService
) {
    fun findAllProperties(
        filterString: String,
        checkIn: LocalDate?,
        checkOut: LocalDate?,
        adults: Int,
        children: Int,
        pet: Int
    ): List<PropertyCardDTO> {

        if (checkOut == null && checkIn != null) return propertyRepository.findAllWithCheckIn(
            filterString,
            checkIn,
            adults,
            children,
            pet
        ).map { dtoMapperService.mapPropertyToPropertyCardDTO(it) }

        if (checkIn == null || checkOut == null) return propertyRepository.findAllWithoutDates(
            filterString,
            adults,
            children,
            pet
        ).map { dtoMapperService.mapPropertyToPropertyCardDTO(it) }


        return propertyRepository.findAllByFilterString(filterString, checkIn, checkOut, adults, children, pet)
            .map { dtoMapperService.mapPropertyToPropertyCardDTO(it) }

    }

    fun findPropertyById(id: Long): Property =
        propertyRepository.findById(id) ?: throw PropertyNotFoundException("Property with id $id not found")

    fun getPropertyDTOById(id: Long): PropertyDTO =
        propertyRepository.findById(id)?.let { dtoMapperService.mapPropertyToDTO(it) }
            ?: throw PropertyNotFoundException("Property with id $id not found")

    @Transactional
    fun saveProperty(property: PropertyRequest, authentication: Authentication?): Property {

        if (authentication == null) throw UnauthorizedAccessException("Please log in to save a property.")

        val host = this.userService.findUserByEmail(authentication.name)
        if (host.dateHostStarted == null) host.dateHostStarted = LocalDate.now()

        val p = propertyRepository.save(
            Property(
                id = property.id,
                nightlyPrice = property.nightlyPrice,
                name = property.name,
                guests = property.guests,
                beds = property.beds,
                bedrooms = property.bedrooms,
                bathrooms = property.bathrooms,
                isGuestFavorite = property.isGuestFavorite,
                description = property.description,
                address = property.address,
                longitude = property.longitude,
                latitude = property.latitude,
                host = host,
                city = property.city,
                propertyType = property.propertyType
            )
        )
        for (image in property.images) {
            imagesService.savePropertyImage(
                PropertyImageRequest(p.id, image.order, image.imageByteArray, image.type)
            )
        }
        for (attribute in property.attributes) {
            propertyAttributesService.save(p, attribute)
        }

        this.propertyAvailabilityRepository.save(
            PropertyAvailability(
                id = 0,
                property = p,
                startDate = LocalDate.now(),
                endDate = LocalDate.now().plusYears(1)
            )
        )

        return p
    }

    fun deletePropertyById(id: Long) = propertyRepository.deleteById(id)


    fun getOfferForProperty(id: Long, offerRequest: OfferRequest): Any {
        val property = findPropertyById(id)
        // TODO: Implement the logic for calculating the offer.
        //  The offer should be calculated based on the nightly price of the property and the number of nights the
        //  guest wants to stay, also check for seasonal prices since the period from start to end can be part of two different seasonal prices

        val range = offerRequest.checkInDate..offerRequest.checkOutDate

        return OfferForBookingDTO(
            start = offerRequest.checkInDate,
            end = offerRequest.checkOutDate,
            totalPrice = 0.0,
            nightlyPrices = mapOf(),
            serviceFee = 10.0,
            cleaningFee = 20.0
        )
    }


    fun getPropertyAvailability(id: Long): List<PropertyAvailabilityDTO> {
        val property = findPropertyById(id)
        return this.propertyAvailabilityRepository.findAllForProperty(property).map {
            PropertyAvailabilityDTO(
                startDate = it.startDate,
                endDate = it.endDate,
            )
        }
    }


    fun suggestProperties(): List<PropertyCardDTO> =
        this.propertyRepository.findAll().map { dtoMapperService.mapPropertyToPropertyCardDTO(it) }
//        this.propertyRepository.suggestProperties().map { this.mapPropertyToPropertyCardDTO(it) }

    @Transactional
    fun reserveProperty(id: Long, offerRequest: OfferRequest, authentication: Authentication?): BookingDTO {
        if (authentication == null) throw UnauthorizedAccessException("Please log in to reserve a property.")

        //TODO: validate checkInDate and checkOutDate

        val guest = this.userService.findUserByEmail(authentication.name)

        val property = findPropertyById(id);

        if (guest.id == property.host.id) throw UnauthorizedAccessException("Hosts cannot book their own properties.")

        if (offerRequest.numberOfAdults > property.guests) throw PropertyCapacityException("Number of adults exceeds the maximum number of guests allowed.")
        if (offerRequest.numberOfChildren > property.guests) throw PropertyCapacityException("Number of children exceeds the maximum number of guests allowed.")
        if (offerRequest.numberOfPets > property.guests) throw PropertyCapacityException("Number of pets exceeds the maximum number of guests allowed.")

        val bookingStatus = bookingStatusRepository.findById(BookingStatusEnum.WAITING_FOR_APPROVAL.ordinal.toLong())
            ?: throw RuntimeException("Booking status not found")

        return bookProperty(offerRequest, property, guest, bookingStatus);


    }

    fun bookProperty(
        offerRequest: OfferRequest,
        property: Property,
        guest: UserAccount,
        bookingStatus: BookingStatus
    ): BookingDTO {
        val propertyAvailability = getPropertyAvailability(
            checkInDate = offerRequest.checkInDate,
            checkOutDate = offerRequest.checkOutDate,
            property = property
        )

        dividePropertyAvailability(propertyAvailability, offerRequest.checkInDate, offerRequest.checkOutDate, property)

        val booking = bookingRepository.save(
            Booking(
                id = 0,
                guest = guest,
                property = property,
                checkIn = offerRequest.checkInDate,
                checkOut = offerRequest.checkOutDate,
                nightlyPrice = property.nightlyPrice,
                serviceFee = 500.0,
                cleaningFee = 1000.0,
                status = bookingStatus,
                bookingDate = LocalDate.now()
            )
        )

        bookingGuestsRepository.save(
            BookingGuest(
                id = 0,
                booking = booking,
                numGuests = offerRequest.numberOfAdults,
                this.guestTypeRepository.findById(GuestTypeEnum.ADULT.ordinal.toLong())!!
            )
        )
        bookingGuestsRepository.save(
            BookingGuest(
                id = 0,
                booking = booking,
                numGuests = offerRequest.numberOfChildren,
                this.guestTypeRepository.findById(GuestTypeEnum.CHILD.ordinal.toLong())!!
            )
        )
        bookingGuestsRepository.save(
            BookingGuest(
                id = 0,
                booking = booking,
                numGuests = offerRequest.numberOfPets,
                this.guestTypeRepository.findById(GuestTypeEnum.PET.ordinal.toLong())!!
            )
        )

        return dtoMapperService.mapBookingToDTO(booking)
    }

    private fun dividePropertyAvailability(
        propertyAvailability: PropertyAvailability,
        checkInDate: LocalDate,
        checkOutDate: LocalDate,
        property: Property
    ) {
        if (propertyAvailability.startDate < checkInDate) {
            this.propertyAvailabilityRepository.save(
                PropertyAvailability(
                    id = 0,
                    property = property,
                    startDate = propertyAvailability.startDate,
                    endDate = checkInDate.minusDays(1)
                )
            )
        }

        if (propertyAvailability.endDate > checkOutDate) {
            this.propertyAvailabilityRepository.save(
                PropertyAvailability(
                    id = 0,
                    property = property,
                    startDate = checkOutDate.plusDays(1),
                    endDate = propertyAvailability.endDate
                )
            )
        }

        this.propertyAvailabilityRepository.deleteById(propertyAvailability.id)
    }

    private fun getPropertyAvailability(
        checkInDate: LocalDate,
        checkOutDate: LocalDate,
        property: Property
    ): PropertyAvailability {
        return this.propertyAvailabilityRepository.findAllForProperty(property)
            .firstOrNull { it.startDate <= checkInDate && it.endDate >= checkOutDate }
            ?: throw PropertyNotAvailableException("Property is not available for the given period: $checkInDate - $checkOutDate")
    }

    fun getPropertyForReview(id: Long, authentication: Authentication?): PropertyCardDTO {
        if (authentication == null) throw UnauthorizedAccessException("Please log in to review a property.")
        if (!this.bookingService.hasFinishedBooking(
                this.findPropertyById(id),
                authentication
            )
        ) throw UnauthorizedAccessException("You must have stayed at the property to leave a review.")
        return dtoMapperService.mapPropertyToPropertyCardDTO(this.findPropertyById(id))
    }

    fun getAllProperties(
        page: Int,
        size: Int,
        filterString: String,
        checkIn: LocalDate?,
        checkOut: LocalDate?,
        adults: Int,
        children: Int,
        pets: Int
    ): PropertyResponse {
        val decoded = URLDecoder.decode(filterString, "UTF-8")
        val pageable = PageRequest.of(page, size)

        if (checkIn == null || checkOut == null)
            if (decoded.isNotBlank())
                return this.propertyRepository.findAllPaginatedByFilterStringWithoutDates(
                    decoded,
                    adults,
                    children,
                    pets,
                    pageable
                ).let {
                    dtoMapperService.mapPropertyPagesToPropertyResponse(it)
                }
            else return this.propertyRepository.findAllPaginated(pageable).let {
                dtoMapperService.mapPropertyPagesToPropertyResponse(it)
            }

        return this.propertyRepository.filterWithPagination(
            decoded,
            checkIn,
            checkOut,
            adults,
            children,
            pets,
            pageable
        ).let {
            dtoMapperService.mapPropertyPagesToPropertyResponse(it)
        }

    }

}

