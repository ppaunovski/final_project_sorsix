package com.sorsix.backend.repository.property_type_repository

import com.sorsix.backend.domain.entities.PropertyType
import org.springframework.data.jpa.repository.JpaRepository

interface JpaPropertyTypeRepository: JpaRepository<PropertyType, Long> {

}