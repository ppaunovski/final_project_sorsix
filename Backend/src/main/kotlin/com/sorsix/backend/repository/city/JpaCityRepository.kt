package com.sorsix.backend.repository.city

import com.sorsix.backend.domain.entities.City
import org.springframework.data.jpa.repository.JpaRepository

interface JpaCityRepository : JpaRepository<City, Long> {
    fun findCitiesByName(name: String): City
}
