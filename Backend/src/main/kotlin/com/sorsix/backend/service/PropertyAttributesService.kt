package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.PropertyAttributeDTO
import com.sorsix.backend.api.dtos.PropertyDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.domain.entities.Attribute
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.PropertyAttribute
import com.sorsix.backend.repository.attribute_repository.AttributeRepository
import com.sorsix.backend.repository.property_attribute_repository.PropertyAttributeRepository
import com.sorsix.backend.repository.property_repository.PropertyRepository
import com.sorsix.backend.service.exceptions.PropertyNotFoundException
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class PropertyAttributesService(
    private val propertyAttributesRepository: PropertyAttributeRepository,
    private val propertyService: PropertyService,
) {

    fun getAllPropertyAttributesForPropertyId(id: Long): List<PropertyAttributeDTO> {
        val property = this.propertyService.findPropertyById(id)

        return this.propertyAttributesRepository.findAllByProperty(property).map {
            prop -> PropertyAttributeDTO(
                id = prop.paId,
                property = propertyService.mapPropertyToDTO(prop.property),
                attribute = prop.attribute
                )

        }.toList()
    }
   fun save(property: Property, attribute: Attribute): PropertyAttribute {
       val propertyAttribute = PropertyAttribute(paId=0,property = property, attribute = attribute)
       return this.propertyAttributesRepository.save(propertyAttribute)
   }
}