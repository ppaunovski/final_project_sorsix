package com.sorsix.backend.repository.property_attribute_repository

import com.sorsix.backend.domain.entities.PropertyAttribute
import org.springframework.data.jpa.repository.JpaRepository

interface JpaPropertyAttributeRepository: JpaRepository<PropertyAttribute, Long> {
}