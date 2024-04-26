package com.sorsix.backend.repository.attribute_repository

import com.sorsix.backend.domain.entities.Attribute
import org.springframework.data.jpa.repository.JpaRepository

interface JpaAttributeRepository: JpaRepository<Attribute, Long> {
}