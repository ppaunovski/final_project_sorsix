package com.sorsix.backend.repository.property_repository

import com.sorsix.backend.domain.entities.Property
import org.springframework.stereotype.Repository

@Repository
class PropertyRepositoryImpl(private val propertyRepository: JpaPropertyRepository): PropertyRepository{
    override fun findAll(): List<Property> =
         propertyRepository.findAll()


    override fun findById(id: Long): Property? =
         propertyRepository.findById(id).orElse(null)

    override fun save(property: Property): Property =
         propertyRepository.save(property)


    override fun deleteById(id: Long) =
        propertyRepository.deleteById(id)

}