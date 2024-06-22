package com.sorsix.backend.repository.property.images

import com.sorsix.backend.domain.entities.PropertyImages

interface PropertyImagesRepository {
    fun findAll(): List<PropertyImages>

    fun findById(id: Long): PropertyImages?

    fun save(propertyImages: PropertyImages): PropertyImages

    fun findThumbnail(propertyId: Long): PropertyImages?

    fun findAllByPropertyId(propertyId: Long): List<PropertyImages>
}
