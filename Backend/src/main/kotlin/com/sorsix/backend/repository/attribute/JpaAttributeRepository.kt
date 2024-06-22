package com.sorsix.backend.repository.attribute

import com.sorsix.backend.domain.entities.Attribute
import org.springframework.data.jpa.repository.JpaRepository

interface JpaAttributeRepository : JpaRepository<Attribute, Long>
