package com.sorsix.backend.repository.property_type_repository

import com.sorsix.backend.domain.entities.PropertyType

interface PropertyTypeRepository {
    fun findAll(): List<PropertyType>
    fun findById(id: Long): PropertyType?
    fun save(propertyType: PropertyType): PropertyType
}