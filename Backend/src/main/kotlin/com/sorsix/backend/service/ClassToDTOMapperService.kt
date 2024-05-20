package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.*
import com.sorsix.backend.api.responses.BookingsResponse
import com.sorsix.backend.api.responses.PropertyResponse
import com.sorsix.backend.api.responses.ReviewsResponse
import com.sorsix.backend.domain.entities.*
import com.sorsix.backend.repository.component_rating_repository.ComponentRatingRepository
import com.sorsix.backend.repository.property_images_repository.PropertyImagesRepository
import com.sorsix.backend.repository.review_component_repository.ReviewComponentRepository
import com.sorsix.backend.repository.user_review_repository.UserReviewRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files

@Service
class ClassToDTOMapperService(
    private val componentRatingRepository: ComponentRatingRepository,
    private val propertyImagesRepository: PropertyImagesRepository,
    private val reviewComponentRepository: UserReviewRepository,
    private val imagesRepository: PropertyImagesRepository
) {

    fun mapPropertyPagesToPropertyResponse(pages: Page<Property>): PropertyResponse {
        return PropertyResponse(
            content = pages.toList().map { this.mapPropertyToPropertyCardDTO(it) },
            totalPages = pages.totalPages,
            totalElements = pages.totalElements,
            page = pages.number,
            size = pages.size,
            last = pages.isLast,
        )
    }

    fun mapUserAccountToDTO(userAccount: UserAccount) =
        UserAccountDTO(
            id = userAccount.id,
            firstName = userAccount.firstName,
            lastName = userAccount.lastName,
            email = userAccount.email,
            joinedDate = userAccount.joinedDate,
            dateHostStarted = userAccount.dateHostStarted
        )

    fun mapUserReviewToReviewDTO(userReview: UserReview): ReviewDTO {
        return ReviewDTO(
            id = userReview.id,
            comment = userReview.comment,
            user = UserAccountDTO(
                id = userReview.user.id,
                email = userReview.user.email,
                firstName = userReview.user.firstName,
                lastName = userReview.user.lastName,
                joinedDate = userReview.user.joinedDate,
                dateHostStarted = userReview.user.dateHostStarted
            ),
            reviewDate = userReview.reviewDate,
            averageRating = this.componentRatingRepository.averageRatingByUserReview(userReview)
        )
    }

    fun mapComponentRatingToDTO(componentRating: ComponentRating): ComponentRatingDTO {
        return ComponentRatingDTO(
            id = componentRating.id,
            rating = componentRating.rating,
            reviewComponent = componentRating.reviewComponent,
            userReview = this.mapUserReviewToReviewDTO(componentRating.userReview)
        )
    }

    fun mapPropertyAttributeToDTO(propertyAttribute: PropertyAttribute): PropertyAttributeDTO {
        return PropertyAttributeDTO(
            id = propertyAttribute.paId,
            property = this.mapPropertyToDTO(propertyAttribute.property),
            attribute = propertyAttribute.attribute
        )
    }

    fun mapToPropertyImageDTO(propertyImages: PropertyImages): PropertyImageDTO {
        return PropertyImageDTO(
            id = propertyImages.id,
            propertyId = propertyImages.property.id,
            order = propertyImages.order,
            imageByteArray = propertyImages.image,
            type = propertyImages.type
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
            images = this.propertyImagesRepository.findAllByPropertyId(property.id).map {
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

    fun mapUserImageToDTO(userImage: UserImage): UserImageDTO {
        return UserImageDTO(
            id = userImage.id,
            image = userImage.image,
            type = userImage.type,
            userId = userImage.user.id
        )
    }

    fun mapPropertyToPropertyCardDTO(property: Property): PropertyCardDTO {
        return PropertyCardDTO(
            id = property.id,
            cityName = property.city.name,
            address = property.address,
            averageRating = this.reviewComponentRepository
                .findAllByProperty(property)
                .map {
                    this.componentRatingRepository.averageRatingByUserReview(it)
                }.average(),
            description = property.description,
            pricePerNight = property.nightlyPrice,
            image = this.imagesRepository.findThumbnail(property.id)?.let { this.mapToPropertyImageDTO(it) }
                ?: PropertyImageDTO(
                    id = 0,
                    propertyId = 0,
                    order = 0,
                    imageByteArray = ByteArray(0),
                    type = ""
                ),
            type = property.propertyType.typeName,
            name = property.name,
            latitude = property.latitude,
            longitude = property.longitude
        )
    }

    fun mapBookingToDTO(booking: Booking): BookingDTO {
        return BookingDTO(
            id = booking.id,
            guest = this.mapUserAccountToDTO(booking.guest),
            property = this.mapPropertyToDTO(booking.property),
            checkIn = booking.checkIn,
            checkOut = booking.checkOut,
            nightlyPrice = booking.nightlyPrice,
            serviceFee = booking.serviceFee,
            cleaningFee = booking.cleaningFee,
            status = booking.status.name
        )
    }

    fun mapBookingPagesToBookingResponse(pages: Page<Booking>): BookingsResponse {
        return BookingsResponse(
            content = pages.toList().map { this.mapBookingToDTO(it) },
            totalPages = pages.totalPages,
            totalElements = pages.totalElements,
            page = pages.number,
            size = pages.size,
            last = pages.isLast
        )

    }

    fun mapReviewPagesToReviewResponse(pages: Page<UserReview>): ReviewsResponse {
        return ReviewsResponse(
            content = pages.toList().map { this.mapUserReviewToReviewDTO(it) },
            totalPages = pages.totalPages,
            totalElements = pages.totalElements,
            page = pages.number,
            size = pages.size,
            last = pages.isLast
        )

    }


}