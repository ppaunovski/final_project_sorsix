package com.sorsix.backend.repository.property.availabilities

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.PropertyAvailability
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface JpaPropertyAvailabilityRepository : JpaRepository<PropertyAvailability, Long> {
    fun findAllByProperty(property: Property): List<PropertyAvailability>

    fun findAllByPropertyAndStartDateAfterAndEndDateBefore(
        property: Property,
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<PropertyAvailability>
}
