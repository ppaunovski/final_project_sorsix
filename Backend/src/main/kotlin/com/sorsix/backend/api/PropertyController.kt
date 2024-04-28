package com.sorsix.backend.api

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.service.PropertyService
import com.sorsix.backend.service.ReviewService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/properties")
class PropertyController(val propertyService: PropertyService,
    private val reviewService: ReviewService) {
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
}