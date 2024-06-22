package com.sorsix.backend.repository.property.attribute

import com.sorsix.backend.domain.entities.Property
import com.sorsix.backend.domain.entities.PropertyAttribute
import org.springframework.data.jpa.repository.JpaRepository

interface JpaPropertyAttributeRepository : JpaRepository<PropertyAttribute, Long> {
    fun findAllByProperty(property: Property): List<PropertyAttribute>
}
