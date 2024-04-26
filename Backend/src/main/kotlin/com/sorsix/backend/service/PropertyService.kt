package com.sorsix.backend.service

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.repository.property_repository.PropertyRepository
import org.springframework.stereotype.Service

@Service
class PropertyService(val propertyRepository: PropertyRepository) {
    fun findAllProperties() = propertyRepository.findAll()
    fun findPropertyById(id: Long) = propertyRepository.findById(id)
    fun saveProperty(property: Property) = propertyRepository.save(property)
    fun deletePropertyById(id: Long) = propertyRepository.deleteById(id)
}