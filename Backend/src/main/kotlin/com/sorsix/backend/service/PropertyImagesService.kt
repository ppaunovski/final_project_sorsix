package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.PropertyImageDTO
import com.sorsix.backend.api.requests.PropertyImageRequest
import com.sorsix.backend.domain.entities.PropertyImages
import com.sorsix.backend.repository.property_images_repository.PropertyImagesRepository
import com.sorsix.backend.repository.property_repository.PropertyRepository
import com.sorsix.backend.service.exceptions.PropertyNotFoundException
import org.springframework.stereotype.Service

@Service
class PropertyImagesService(
    private val propertyImagesRepository: PropertyImagesRepository,
    private val propertyRepository: PropertyRepository,
    private val dtoMapperService: ClassToDTOMapperService
) {
    fun getAllPropertyImagesForPropertyId(id: Long): List<PropertyImageDTO> {
        return this.propertyImagesRepository.findAllByPropertyId(id).map {
            dtoMapperService.mapToPropertyImageDTO(it)
        }
    }


    fun getAll(): List<PropertyImageDTO> =
        this.propertyImagesRepository.findAll().map {
            dtoMapperService.mapToPropertyImageDTO(it)
        }.toList()



    fun getNextOrder(id: Long): Int {
        val propertyImages = this.propertyImagesRepository.findAllByPropertyId(id)
        return if (propertyImages.isEmpty()) 0 else propertyImages.maxBy { it.order }.order + 1

    }

    fun savePropertyImage(propertyImageRequest: PropertyImageRequest) {
        val property = this.propertyRepository.findById(propertyImageRequest.propertyId) ?: throw PropertyNotFoundException("Property with id ${propertyImageRequest.propertyId} not found")
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

