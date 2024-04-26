package com.sorsix.backend.repository.property_repository

import com.sorsix.backend.domain.entities.City
import com.sorsix.backend.domain.entities.Property
import org.springframework.data.jpa.repository.JpaRepository

interface JpaPropertyRepository: JpaRepository<Property, Long>{
}