package com.sorsix.backend.repository.property_images_repository

import com.sorsix.backend.domain.entities.PropertyImages

interface PropertyImagesRepository {
    fun findAll(): List<PropertyImages>
    fun findById(id: Long): PropertyImages?
    fun save(propertyImages: PropertyImages): PropertyImages
}