package com.sorsix.backend.repository.favorite

import com.sorsix.backend.domain.entities.Favorite

interface FavoriteRepository {
    fun findAll(): List<Favorite>

    fun findById(id: Long): Favorite?

    fun save(favorite: Favorite): Favorite

    fun findByUserId(userId: Long): List<Favorite>

    fun findByUserIdAndPropertyId(
        userId: Long,
        propertyId: Long,
    ): Favorite?

    fun deleteByUserIdAndPropertyId(
        userId: Long,
        propertyId: Long,
    )

    fun existsByUserIdAndPropertyId(
        userId: Long,
        propertyId: Long,
    ): Boolean
}
