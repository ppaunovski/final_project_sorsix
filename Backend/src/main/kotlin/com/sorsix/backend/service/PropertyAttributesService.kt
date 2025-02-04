package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.PropertyAttributeDTO
import com.sorsix.backend.domain.entities.Attribute
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.PropertyAttribute
import com.sorsix.backend.repository.property.attribute.PropertyAttributeRepository
import com.sorsix.backend.repository.property.repository.PropertyRepository
import com.sorsix.backend.service.exceptions.PropertyNotFoundException
import org.springframework.stereotype.Service

@Service
class PropertyAttributesService(
    private val propertyAttributesRepository: PropertyAttributeRepository,
    private val propertyRepository: PropertyRepository,
    private val dtoMapperService: ClassToDTOMapperService,
) {
    fun getAllPropertyAttributesForPropertyId(id: Long): List<PropertyAttributeDTO> {
        val property = this.propertyRepository.findById(id) ?: throw PropertyNotFoundException("Property with id $id not found")

        return this.propertyAttributesRepository
            .findAllByProperty(property)
            .map {
                dtoMapperService.mapPropertyAttributeToDTO(it)
            }.toList()
    }

    fun save(
        property: Property,
        attribute: Attribute,
    ): PropertyAttribute {
        val propertyAttribute = PropertyAttribute(paId = 0, property = property, attribute = attribute)
        return this.propertyAttributesRepository.save(propertyAttribute)
    }
}
