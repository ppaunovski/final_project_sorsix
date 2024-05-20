package com.sorsix.backend.repository.favorite_repository

import com.sorsix.backend.domain.entities.Favorite
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class FavoriteRepositoryImpl(
        private val favoriteRepository: JpaFavoriteRepository
) : FavoriteRepository {
    override fun findAll(): List<Favorite> =
            this.favoriteRepository.findAll()

    override fun findById(id: Long): Favorite? =
            this.favoriteRepository.findByIdOrNull(id)

    override fun save(favorite: Favorite): Favorite =
            this.favoriteRepository.save(favorite)

    override fun findByUserId(userId: Long): List<Favorite> {
        return this.favoriteRepository.findByUserId(userId)
    }

    override fun findByUserIdAndPropertyId(userId: Long, propertyId: Long): Favorite? {
        return this.favoriteRepository.findByUserIdAndPropertyId(userId, propertyId)
    }

    @Transactional
    override fun deleteByUserIdAndPropertyId(userId: Long, propertyId: Long) {
        this.favoriteRepository.deleteByUserIdAndPropertyId(userId, propertyId)
    }

    override fun existsByUserIdAndPropertyId(userId: Long, propertyId: Long): Boolean {
        return this.favoriteRepository.existsByUserIdAndPropertyId(userId, propertyId)
    }
}