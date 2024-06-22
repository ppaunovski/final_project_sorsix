package com.sorsix.backend.repository.property.attribute

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.PropertyAttribute

interface PropertyAttributeRepository {
    fun findAll(): List<PropertyAttribute>

    fun findById(id: Long): PropertyAttribute?

    fun save(propertyAttribute: PropertyAttribute): PropertyAttribute

    fun findAllByProperty(property: Property): List<PropertyAttribute>
}
