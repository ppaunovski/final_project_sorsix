package com.sorsix.backend.repository.property.type

import com.sorsix.backend.domain.entities.PropertyType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PropertyTypeRepositoryImpl(
    private val jpaPropertyTypeRepository: JpaPropertyTypeRepository,
) : PropertyTypeRepository {
    override fun findAll(): List<PropertyType> = this.jpaPropertyTypeRepository.findAll()

    override fun findById(id: Long): PropertyType? = this.jpaPropertyTypeRepository.findByIdOrNull(id)

    override fun save(propertyType: PropertyType): PropertyType = this.jpaPropertyTypeRepository.save(propertyType)
}
