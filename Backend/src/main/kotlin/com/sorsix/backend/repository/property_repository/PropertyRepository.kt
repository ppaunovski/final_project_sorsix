package com.sorsix.backend.repository.property_repository

import com.sorsix.backend.domain.entities.Property

interface PropertyRepository {
    fun findAll(): List<Property>
    fun findById(id: Long): Property?
    fun save(property: Property): Property
    fun deleteById(id: Long)

}