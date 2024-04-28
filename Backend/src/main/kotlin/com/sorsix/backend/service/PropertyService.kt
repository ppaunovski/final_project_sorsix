package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.PropertyDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.repository.property_repository.PropertyRepository
import com.sorsix.backend.service.exceptions.PropertyNotFoundException
import org.springframework.stereotype.Service

@Service
class PropertyService(private val propertyRepository: PropertyRepository,) {
    fun findAllProperties(): List<PropertyDTO> =
        propertyRepository.findAll().map {
            mapPropertyToDTO(it)
        }
    fun findPropertyById(id: Long): Property =
        propertyRepository.findById(id) ?: throw PropertyNotFoundException(id)

    fun getPropertyDTOById(id: Long): PropertyDTO =
        propertyRepository.findById(id)?.let { this.mapPropertyToDTO(it) } ?: throw PropertyNotFoundException(id)

    fun saveProperty(property: Property) = propertyRepository.save(property)

    fun deletePropertyById(id: Long) = propertyRepository.deleteById(id)

    fun mapPropertyToDTO(property: Property): PropertyDTO {
        return PropertyDTO(
            id = property.id,
            name = property.name,
            description = property.description,
            nightlyPrice = property.nightlyPrice,
            address = property.address,
            guests = property.guests,
            beds = property.beds,
            bedrooms = property.bedrooms,
            bathrooms = property.bathrooms,
            isGuestFavorite = property.isGuestFavorite,
            longitude = property.longitude,
            latitude = property.latitude,
            host = UserAccountDTO(
                id = property.host.id,
                email = property.host.email,
                firstName = property.host.firstName,
                lastName = property.host.lastName,
                joinedDate = property.host.joinedDate,
                dateHostStarted = property.host.dateHostStarted,
            ),
            city = property.city,
            propertyType = property.propertyType
        )
    }
}