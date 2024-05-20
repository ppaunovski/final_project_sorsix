package com.sorsix.backend.repository.favorite_repository

import com.sorsix.backend.domain.entities.Favorite
import org.springframework.data.jpa.repository.JpaRepository

interface JpaFavoriteRepository: JpaRepository<Favorite, Long> {
    fun findByUserId(userId: Long): List<Favorite>
    fun findByUserIdAndPropertyId(userId: Long, propertyId: Long): Favorite?
    fun deleteByUserIdAndPropertyId(userId: Long, propertyId: Long)
    fun existsByUserIdAndPropertyId(userId: Long, propertyId: Long): Boolean
}