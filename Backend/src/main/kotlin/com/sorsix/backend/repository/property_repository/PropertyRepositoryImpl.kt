package com.sorsix.backend.repository.property_repository

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class PropertyRepositoryImpl(private val propertyRepository: JpaPropertyRepository): PropertyRepository{
    override fun findAll(): List<Property> =
         propertyRepository.findAll()

    override fun findAllPaginated(pageable: Pageable): Page<Property> =
        this.propertyRepository.findAllPaginated(pageable)



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

    override fun findAllByHost(host: UserAccount): List<Property> =
        this.propertyRepository.findAllByHost(host)

    override fun findAllWithoutDates(filterString: String, adults: Int, children: Int, pet: Int): List<Property> =
        this.propertyRepository.findWithoutDates(filterString, adults, children, pet)

    override fun findAllWithCheckIn(filterString: String,checkIn: LocalDate, adults: Int, children: Int, pets: Int): List<Property> =
        this.propertyRepository.findWithCheckIn(filterString, checkIn, adults, children, pets)

    override fun filterWithPagination(
        filterString: String,
        checkIn: LocalDate,
        checkOut: LocalDate,
        adults: Int,
        children: Int,
        pets: Int,
        pageable: Pageable
    ): Page<Property> =
        this.propertyRepository.filterWithPagination(filterString, checkIn, checkOut, adults, children, pets, pageable)

    override fun findAllPaginatedByFilterStringWithoutDates(filterString: String, adults: Int, children: Int, pets: Int, pageable: Pageable): Page<Property> =
        this.propertyRepository.findAllByFilterString(filterString, adults, children, pets, pageable)

    override fun getNearest(lat: Double, lng: Double): List<Property> =
        this.propertyRepository.getNearest(lat, lng)

}