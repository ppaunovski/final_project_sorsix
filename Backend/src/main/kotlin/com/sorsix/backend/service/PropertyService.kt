package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.OfferForBookingDTO
import com.sorsix.backend.api.dtos.PropertyCardDTO
import com.sorsix.backend.api.dtos.PropertyDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.api.requests.OfferRequest
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.repository.property_repository.PropertyRepository
import com.sorsix.backend.repository.user_review_repository.UserReviewRepository
import com.sorsix.backend.service.exceptions.PropertyNotFoundException
import org.springframework.stereotype.Service

@Service
class PropertyService(private val propertyRepository: PropertyRepository,
    private val reviewRepository: UserReviewRepository,
    private val componentRatingService: ComponentRatingService) {
    fun findAllProperties(): List<PropertyCardDTO> =
        propertyRepository.findAll().map { property ->
            PropertyCardDTO(
                id = property.id,
                cityName = property.city.name,
                address = property.address,
                averageRating = this.reviewRepository
                    .findAllByProperty(property)
                    .map { this.componentRatingService
                        .findAverageComponentRatingForUserReview(it.id)
                    }.average(),
                description = property.description,
                pricePerNight = property.nightlyPrice
            )
        }
    fun findPropertyById(id: Long): Property =
        propertyRepository.findById(id) ?: throw PropertyNotFoundException(id)

    fun getPropertyDTOById(id: Long): PropertyDTO =
        propertyRepository.findById(id)?.let { this.mapPropertyToDTO(it) } ?: throw PropertyNotFoundException(id)

    fun saveProperty(property: Property) = propertyRepository.save(property)

    fun deletePropertyById(id: Long) = propertyRepository.deleteById(id)

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
            propertyType = property.propertyType
        )
    }

    fun getOfferForProperty(id: Long, offerRequest: OfferRequest): Any {
        val property = findPropertyById(id)
        // TODO: Implement the logic for calculating the offer.
        //  The offer should be calculated based on the nightly price of the property and the number of nights the
        //  guest wants to stay, also check for seasonal prices since the period from start to end can be part of two different seasonal prices

        val range = offerRequest.start..offerRequest.end

        return OfferForBookingDTO(
            start = offerRequest.start,
            end = offerRequest.end,
            totalPrice = 0.0,
            nightlyPrices = mapOf(),
            serviceFee = 10.0,
            cleaningFee = 20.0
        )
    }

    fun createBookingForProperty(id: Long, offerRequest: OfferRequest): Any {
       TODO()

    }
}