package com.sorsix.backend.repository.property_repository

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate

interface PropertyRepository {
    fun findAll(): List<Property>
    fun findAllPaginated(pageable: Pageable): Page<Property>
    fun findById(id: Long): Property?
    fun save(property: Property): Property
    fun deleteById(id: Long)
    fun findAllByFilterString(filterString: String, checkIn: LocalDate, checkOut: LocalDate, adults: Int, children: Int, pets: Int): List<Property>
    fun suggestProperties(): List<Property>
    fun findAllByHost(host: UserAccount): List<Property>
    fun findAllWithoutDates(filterString: String, adults: Int, children: Int, pet: Int): List<Property>
    fun findAllWithCheckIn(filterString: String,checkIn: LocalDate, adults: Int, children: Int, pets: Int): List<Property>
    fun filterWithPagination(filterString: String, checkIn: LocalDate, checkOut: LocalDate, adults: Int, children: Int, pets: Int, pageable: Pageable): Page<Property>
    fun findAllPaginatedByFilterStringWithoutDates(filterString: String, adults: Int, children: Int, pets: Int, pageable: Pageable): Page<Property>
}