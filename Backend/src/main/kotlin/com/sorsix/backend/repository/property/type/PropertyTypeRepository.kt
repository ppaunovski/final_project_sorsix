package com.sorsix.backend.repository.property.type

import com.sorsix.backend.domain.entities.PropertyType

interface PropertyTypeRepository {
    fun findAll(): List<PropertyType>

    fun findById(id: Long): PropertyType?

    fun save(propertyType: PropertyType): PropertyType
}
