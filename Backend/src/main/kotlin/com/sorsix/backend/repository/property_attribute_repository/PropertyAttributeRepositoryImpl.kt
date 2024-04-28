package com.sorsix.backend.repository.property_attribute_repository

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.PropertyAttribute
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PropertyAttributeRepositoryImpl(
        private val jpaPropertyAttributeRepository: JpaPropertyAttributeRepository
) : PropertyAttributeRepository{
    override fun findAll(): List<PropertyAttribute> =
            this.jpaPropertyAttributeRepository.findAll()
    override fun findById(id: Long): PropertyAttribute? =
            this.jpaPropertyAttributeRepository.findByIdOrNull(id)

    override fun save(propertyAttribute: PropertyAttribute): PropertyAttribute =
            this.jpaPropertyAttributeRepository.save(propertyAttribute)

    override fun findAllByProperty(property: Property): List<PropertyAttribute> =
        this.jpaPropertyAttributeRepository.findAllByProperty(property)
}