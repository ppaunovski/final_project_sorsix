package com.sorsix.backend.repository.property_availabilities_repository

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.PropertyAvailability
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PropertyAvailabilityRepositoryImpl(
    private val jpaPropertyAvailabilityRepository: JpaPropertyAvailabilityRepository
) : PropertyAvailabilityRepository {
    override fun findAllForProperty(property: Property): List<PropertyAvailability> =
        this.jpaPropertyAvailabilityRepository.findAllByProperty(property);

    override fun findById(id: Long): PropertyAvailability? =
        this.jpaPropertyAvailabilityRepository.findByIdOrNull(id)

    override fun save(propertyAvailability: PropertyAvailability): PropertyAvailability =
        this.jpaPropertyAvailabilityRepository.save(propertyAvailability)

    override fun deleteById(id: Long) =
        this.jpaPropertyAvailabilityRepository.deleteById(id)

    override fun edit(propertyAvailability: PropertyAvailability): PropertyAvailability {
        TODO()
    }

}