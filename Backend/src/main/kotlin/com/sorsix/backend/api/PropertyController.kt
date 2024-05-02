package com.sorsix.backend.api

import com.sorsix.backend.api.requests.OfferRequest
import com.sorsix.backend.api.requests.PropertyImageRequest
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.service.PropertyImagesService
import com.sorsix.backend.service.PropertyService
import com.sorsix.backend.service.ReviewService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/properties")
class PropertyController(
    private val propertyService: PropertyService,
    private val reviewService: ReviewService,
    private val propertyImagesService: PropertyImagesService){
    @GetMapping
fun findAllProperties() = propertyService.findAllProperties()

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


}