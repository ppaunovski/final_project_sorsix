package com.sorsix.backend.repository.attribute_repository

import com.sorsix.backend.domain.entities.Attribute
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class AttributeRepositoryImpl(
        private val jpaAttributeRepository: JpaAttributeRepository
): AttributeRepository {
    override fun findAll(): List<Attribute> =
            this.jpaAttributeRepository.findAll()

    override fun findById(id: Long): Attribute? =
            this.jpaAttributeRepository.findByIdOrNull(id)

    override fun save(attribute: Attribute): Attribute =
            this.jpaAttributeRepository.save(attribute)
}