package com.sorsix.backend.api

import com.sorsix.backend.api.requests.OfferRequest
import com.sorsix.backend.api.requests.PropertyImageRequest
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.service.ComponentRatingService
import com.sorsix.backend.service.PropertyImagesService
import com.sorsix.backend.service.PropertyService
import com.sorsix.backend.service.ReviewService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

@RestController
@RequestMapping("/api/properties")
class PropertyController(
    private val propertyService: PropertyService,
    private val reviewService: ReviewService,
    private val propertyImagesService: PropertyImagesService,
    private val componentRatingService: ComponentRatingService
    ){
    @GetMapping("/search")
    fun findAllProperties(
        @RequestParam filterString: String,
        @RequestParam checkIn: LocalDate,
        @RequestParam checkOut: LocalDate,
        @RequestParam adults: Int,
        @RequestParam children: Int,
        @RequestParam pets: Int,
    ) = propertyService.findAllProperties(filterString, checkIn, checkOut, adults, children, pets)

    @GetMapping()
    fun suggestProperties() = propertyService.suggestProperties()

    @GetMapping("/{id}")
    fun findPropertyById(@PathVariable id: Long) = propertyService.getPropertyDTOById(id)

    @PostMapping
    fun saveProperty(@RequestBody property: Property) = propertyService.saveProperty(property)

    @DeleteMapping("/{id}")
    fun deletePropertyById(@PathVariable id: Long) = propertyService.deletePropertyById(id)

    @GetMapping("/{id}/reviews")
    fun findAllReviewsForProperty(@PathVariable id: Long) = reviewService.getAllReviewsForProperty(id)

    @PostMapping("/{id}/images")
    fun savePropertyImage(@PathVariable id: Long, @RequestBody image: MultipartFile) =
        propertyImagesService.savePropertyImage(
            PropertyImageRequest(id, propertyImagesService.getNextOrder(id), image.bytes)
        )

    @GetMapping("/{id}/get-offer")
    fun getOfferForProperty(@PathVariable id: Long, @RequestBody offerRequest: OfferRequest) = propertyService.getOfferForProperty(id, offerRequest)

    @PostMapping("/{id}/book")
    fun createBookingForProperty(@PathVariable id: Long,
                                 @RequestBody offerRequest: OfferRequest) =
        propertyService.createBookingForProperty(id, offerRequest)

    @GetMapping("/{id}/availability")
    fun getPropertyAvailability(@PathVariable id: Long) = propertyService.getPropertyAvailability(id)

    @GetMapping("/{id}/average-component-ratings")
    fun getAverageComponentRatingsForProperty(@PathVariable id: Long) = componentRatingService.findAverageComponentRatingAverageForProperty(id)

}