package com.sorsix.backend.repository.favorite_repository

import com.sorsix.backend.domain.entities.ComponentRating
import com.sorsix.backend.domain.entities.Favorite

interface FavoriteRepository {
    fun findAll(): List<Favorite>
    fun findById(id: Long): Favorite?
    fun save(favorite: Favorite): Favorite
}