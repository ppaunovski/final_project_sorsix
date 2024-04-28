package com.sorsix.backend.api

import com.sorsix.backend.api.dtos.PropertyAttributeDTO
import com.sorsix.backend.domain.entities.Attribute
import com.sorsix.backend.domain.entities.PropertyAttribute
import com.sorsix.backend.service.PropertyAttributesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/property-attributes")
class PropertyAttributeController(private val service: PropertyAttributesService) {
    @GetMapping("/{id}")
    fun getPropertyAttributesForProperty(@PathVariable id: Long): List<PropertyAttributeDTO> {
        return this.service.getAllPropertyAttributesForPropertyId(id)
    }
}