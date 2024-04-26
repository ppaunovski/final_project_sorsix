package com.sorsix.backend.api

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.service.PropertyService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/property")
class PropertyController(val propertyService: PropertyService) {
    @GetMapping
    fun findAllProperties() = propertyService.findAllProperties()
    @GetMapping("/{id}")
    fun findPropertyById(@PathVariable id: Long) = propertyService.findPropertyById(id)
    @PostMapping
    fun saveProperty(@RequestBody property: Property) = propertyService.saveProperty(property)
    @DeleteMapping("/{id}")
    fun deletePropertyById(@PathVariable id: Long) = propertyService.deletePropertyById(id)
}