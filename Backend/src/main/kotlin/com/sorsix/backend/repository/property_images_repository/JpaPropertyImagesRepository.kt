package com.sorsix.backend.repository.property_images_repository

import com.sorsix.backend.domain.entities.PropertyImages
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaPropertyImagesRepository: JpaRepository<PropertyImages, Long> {
    fun findAllByPropertyId(propertyId: Long): List<PropertyImages>
    @Query("SELECT pi FROM PropertyImages pi WHERE pi.property.id = :propertyId AND pi.order = 0")
    fun findThumbnail(propertyId: Long): PropertyImages?
}