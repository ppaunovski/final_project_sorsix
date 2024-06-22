package com.sorsix.backend.repository.attribute

import com.sorsix.backend.domain.entities.Attribute

interface AttributeRepository {
    fun findAll(): List<Attribute>

    fun findById(id: Long): Attribute?

    fun save(attribute: Attribute): Attribute
}
