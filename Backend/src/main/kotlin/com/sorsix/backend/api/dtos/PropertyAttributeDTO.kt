package com.sorsix.backend.api.dtos

import com.sorsix.backend.domain.entities.Attribute
import com.sorsix.backend.domain.entities.Property
import jakarta.persistence.*

data class PropertyAttributeDTO (
    val id: Long,
    val property: PropertyDTO,
    val attribute: Attribute
)