package com.sorsix.backend.repository.property_repository

import com.sorsix.backend.domain.entities.Property
import org.springframework.stereotype.Repository
import java.time.LocalDate

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

    override fun findAllByFilterString(
        filterString: String,
        checkIn: LocalDate,
        checkOut: LocalDate,
        adults: Int,
        children: Int,
        pets: Int) =
            this.propertyRepository.search(filterString, checkIn, checkOut, adults, children, pets)

    override fun suggestProperties(): List<Property> =
        this.propertyRepository.suggestProperties()

}