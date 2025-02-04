package com.sorsix.backend.repository.property.images

import com.sorsix.backend.domain.entities.PropertyImages
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PropertyImagesRepositoryImpl(
    private val jpaPropertyImagesRepository: JpaPropertyImagesRepository,
) : PropertyImagesRepository {
    override fun findAll(): List<PropertyImages> = this.jpaPropertyImagesRepository.findAll()

    override fun findById(id: Long): PropertyImages? = this.jpaPropertyImagesRepository.findByIdOrNull(id)

    override fun save(propertyImages: PropertyImages): PropertyImages = this.jpaPropertyImagesRepository.save(propertyImages)

    override fun findThumbnail(propertyId: Long): PropertyImages? = this.jpaPropertyImagesRepository.findThumbnail(propertyId)

    override fun findAllByPropertyId(propertyId: Long): List<PropertyImages> =
        this.jpaPropertyImagesRepository.findAllByPropertyId(propertyId)
}
