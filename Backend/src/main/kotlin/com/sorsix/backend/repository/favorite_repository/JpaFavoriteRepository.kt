package com.sorsix.backend.repository.favorite_repository

import com.sorsix.backend.domain.entities.Favorite
import org.springframework.data.jpa.repository.JpaRepository

interface JpaFavoriteRepository: JpaRepository<Favorite, Long> {
}