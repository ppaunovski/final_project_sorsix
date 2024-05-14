package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.PropertyImageDTO
import com.sorsix.backend.api.requests.PropertyImageRequest
import com.sorsix.backend.domain.entities.PropertyImages
import com.sorsix.backend.repository.property_images_repository.PropertyImagesRepository
import org.springframework.stereotype.Service

@Service
class PropertyImagesService(
    private val propertyImagesRepository: PropertyImagesRepository,
    private val propertyService: PropertyService
) {
    fun getAllPropertyImagesForPropertyId(id: Long): List<PropertyImageDTO> {
        return this.propertyImagesRepository.findAllByPropertyId(id).map {
            mapToPropertyImageDTO(it)
        }.toList()
    }


    fun getAll(): List<PropertyImageDTO> =
        this.propertyImagesRepository.findAll().map {
            mapToPropertyImageDTO(it)
        }.toList()

    private fun mapToPropertyImageDTO(propertyImages: PropertyImages): PropertyImageDTO {
        return PropertyImageDTO(
            id = propertyImages.id,
            propertyId = propertyImages.property.id,
            order = propertyImages.order,
            imageByteArray = propertyImages.image,
            type = propertyImages.type
        )
    }

    fun getNextOrder(id: Long): Int {
        val propertyImages = this.propertyImagesRepository.findAllByPropertyId(id)
        return if (propertyImages.isEmpty()) 0 else propertyImages.maxBy { it.order }.order + 1

    }

    fun savePropertyImage(propertyImageRequest: PropertyImageRequest) {
        val property = this.propertyService.findPropertyById(propertyImageRequest.propertyId)
        val propertyImages = PropertyImages(
            id = 0,
            property = property,
            order = getNextOrder(property.id),
            image = propertyImageRequest.image,
            type = propertyImageRequest.type
        )
        this.propertyImagesRepository.save(propertyImages)

    }
}

