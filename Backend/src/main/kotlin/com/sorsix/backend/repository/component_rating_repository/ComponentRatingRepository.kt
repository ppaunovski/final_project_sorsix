package com.sorsix.backend.repository.component_rating_repository

import com.sorsix.backend.domain.entities.Attribute
import com.sorsix.backend.domain.entities.ComponentRating

interface ComponentRatingRepository {
    fun findAll(): List<ComponentRating>
    fun findById(id: Long): ComponentRating?
    fun save(componentRating: ComponentRating): ComponentRating
}