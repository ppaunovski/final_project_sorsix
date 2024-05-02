package com.sorsix.backend.repository.property_availabilities_repository

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.PropertyAvailability

interface PropertyAvailabilityRepository {
    fun findAllForProperty(property: Property): List<PropertyAvailability>
    fun findById(id: Long): PropertyAvailability?
    fun save(propertyAvailability: PropertyAvailability): PropertyAvailability
    fun deleteById(id: Long)
    fun edit(propertyAvailability: PropertyAvailability): PropertyAvailability
}