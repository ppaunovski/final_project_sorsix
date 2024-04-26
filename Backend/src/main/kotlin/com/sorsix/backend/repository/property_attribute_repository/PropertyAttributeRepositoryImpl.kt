package com.sorsix.backend.repository.property_attribute_repository

import com.sorsix.backend.domain.entities.PropertyAttribute
import org.springframework.data.repository.findByIdOrNull

class PropertyAttributeRepositoryImpl(
        private val jpaPropertyAttributeRepository: JpaPropertyAttributeRepository
) : PropertyAttributeRepository{
    override fun findAll(): List<PropertyAttribute> =
            this.jpaPropertyAttributeRepository.findAll()
    override fun findById(id: Long): PropertyAttribute? =
            this.jpaPropertyAttributeRepository.findByIdOrNull(id)

    override fun save(propertyAttribute: PropertyAttribute): PropertyAttribute =
            this.jpaPropertyAttributeRepository.save(propertyAttribute)
}