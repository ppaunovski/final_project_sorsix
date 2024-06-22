package com.sorsix.backend.api.dtos

import com.sorsix.backend.domain.entities.Attribute

data class PropertyAttributeDTO(
    val id: Long,
    val property: PropertyDTO,
    val attribute: Attribute,
)
