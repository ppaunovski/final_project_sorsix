package com.sorsix.backend.api

import com.sorsix.backend.repository.property.type.PropertyTypeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/property-type")
class PropertyTypeController(
    private val propertyTypeRepository: PropertyTypeRepository,
) {
    @GetMapping("/all")
    fun getAllPropertyTypes() = propertyTypeRepository.findAll()

    @GetMapping("/{id}")
    fun getPropertyTypeById(
        @PathVariable id: Long,
    ) = propertyTypeRepository.findById(id)
}
