package com.sorsix.backend.repository.property_repository

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.UserAccount
import java.time.LocalDate

interface PropertyRepository {
    fun findAll(): List<Property>
    fun findById(id: Long): Property?
    fun save(property: Property): Property
    fun deleteById(id: Long)
    fun findAllByFilterString(filterString: String, checkIn: LocalDate, checkOut: LocalDate, adults: Int, children: Int, pets: Int): List<Property>
    fun suggestProperties(): List<Property>
    fun findAllByHost(host: UserAccount): List<Property>
}