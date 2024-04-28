package com.sorsix.backend.repository.property_attribute_repository

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.PropertyAttribute
import com.sorsix.backend.domain.entities.PropertyType

interface PropertyAttributeRepository {
    fun findAll(): List<PropertyAttribute>
    fun findById(id: Long): PropertyAttribute?
    fun save(propertyAttribute: PropertyAttribute): PropertyAttribute
    fun findAllByProperty(property: Property): List<PropertyAttribute>
}