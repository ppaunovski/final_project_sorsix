package com.sorsix.backend.repository.favorite_repository

import com.sorsix.backend.domain.entities.Favorite
import org.springframework.data.repository.findByIdOrNull

class FavoriteRepositoryImpl(
        private val jpaFavoriteRepository: JpaFavoriteRepository
) : FavoriteRepository {
    override fun findAll(): List<Favorite> =
            this.jpaFavoriteRepository.findAll()

    override fun findById(id: Long): Favorite? =
            this.jpaFavoriteRepository.findByIdOrNull(id)

    override fun save(favorite: Favorite): Favorite =
            this.jpaFavoriteRepository.save(favorite)
}