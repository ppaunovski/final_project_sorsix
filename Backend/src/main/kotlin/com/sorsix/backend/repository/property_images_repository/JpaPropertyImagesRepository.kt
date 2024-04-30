package com.sorsix.backend.repository.property_images_repository

import com.sorsix.backend.domain.entities.PropertyImages
import org.springframework.data.jpa.repository.JpaRepository

interface JpaPropertyImagesRepository: JpaRepository<PropertyImages, Long> {
    fun findAllByPropertyId(propertyId: Long): List<PropertyImages>
}