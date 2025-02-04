package com.sorsix.backend.api

import com.sorsix.backend.api.requests.OfferRequest
import com.sorsix.backend.api.requests.PropertyImageRequest
import com.sorsix.backend.api.requests.PropertyRequest
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.service.ComponentRatingService
import com.sorsix.backend.service.PropertyImagesService
import com.sorsix.backend.service.PropertyService
import com.sorsix.backend.service.ReviewService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

@RestController
@RequestMapping("/api/properties")
class PropertyController(
    private val propertyService: PropertyService,
    private val reviewService: ReviewService,
    private val propertyImagesService: PropertyImagesService,
    private val componentRatingService: ComponentRatingService,
) {
    @GetMapping("/nearest")
    fun getNearest(
        @RequestParam(required = false) lat: Double,
        @RequestParam(required = false) lng: Double,
    ) = propertyService.getNearest(lat, lng)

    @GetMapping("/pagination")
    fun getAllProperties(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
        @RequestParam(required = false, defaultValue = "") filterString: String,
        @RequestParam(required = false) checkIn: LocalDate?,
        @RequestParam(required = false) checkOut: LocalDate?,
        @RequestParam(required = false, defaultValue = "1") adults: Int,
        @RequestParam(required = false, defaultValue = "0") children: Int,
        @RequestParam(required = false, defaultValue = "0") pets: Int,
    ) = propertyService.getAllProperties(page, size, filterString, checkIn, checkOut, adults, children, pets)

    @GetMapping("/search")
    fun findAllProperties(
        @RequestParam filterString: String,
        @RequestParam(required = false) checkIn: LocalDate?,
        @RequestParam(required = false) checkOut: LocalDate?,
        @RequestParam adults: Int,
        @RequestParam children: Int,
        @RequestParam pets: Int,
    ) = propertyService.findAllProperties(filterString, checkIn, checkOut, adults, children, pets)

    @GetMapping()
    fun suggestProperties() = propertyService.suggestProperties()

    @GetMapping("/{id}")
    fun findPropertyById(
        @PathVariable id: Long,
    ) = propertyService.getPropertyDTOById(id)

    @PostMapping
    fun saveProperty(
        @RequestBody property: PropertyRequest,
        authentication: Authentication?,
    ): Property = propertyService.saveProperty(property, authentication)

    @DeleteMapping("/{id}")
    fun deletePropertyById(
        @PathVariable id: Long,
    ) = propertyService.deletePropertyById(id)

    @GetMapping("/{id}/reviews")
    fun findAllReviewsForProperty(
        @PathVariable id: Long,
    ) = reviewService.getAllReviewsForProperty(id)

    @PostMapping("/{id}/images")
    fun savePropertyImage(
        @PathVariable id: Long,
        @RequestBody image: MultipartFile,
    ) = propertyImagesService.savePropertyImage(
        PropertyImageRequest(id, propertyImagesService.getNextOrder(id), image.bytes, image.contentType!!),
    )

    @GetMapping("/{id}/get-offer")
    fun getOfferForProperty(
        @PathVariable id: Long,
        @RequestBody offerRequest: OfferRequest,
    ) = propertyService.getOfferForProperty(id, offerRequest)

    @PostMapping("/{id}/reserve")
    fun reserveProperty(
        @PathVariable id: Long,
        @RequestBody offerRequest: OfferRequest,
        authentication: Authentication?,
    ) = propertyService.reserveProperty(id, offerRequest, authentication)

    @GetMapping("/{id}/availability")
    fun getPropertyAvailability(
        @PathVariable id: Long,
    ) = propertyService.getPropertyAvailability(id)

    @GetMapping("/{id}/average-component-ratings")
    fun getAverageComponentRatingsForProperty(
        @PathVariable id: Long,
    ) = componentRatingService.findAverageComponentRatingAverageForProperty(id)

    @GetMapping("{id}/for-review")
    fun getPropertyForReview(
        @PathVariable id: Long,
        authentication: Authentication?,
    ) = propertyService.getPropertyForReview(id, authentication)
}
