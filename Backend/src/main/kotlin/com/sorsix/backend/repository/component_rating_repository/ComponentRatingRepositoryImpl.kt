package com.sorsix.backend.repository.component_rating_repository

import com.sorsix.backend.domain.entities.ComponentRating
import org.springframework.data.repository.findByIdOrNull

class ComponentRatingRepositoryImpl(
        private val jpaComponentRatingRepository: JpaComponentRatingRepository
) : ComponentRatingRepository{
    override fun findAll(): List<ComponentRating> =
            this.jpaComponentRatingRepository.findAll()

    override fun findById(id: Long): ComponentRating? =
            this.jpaComponentRatingRepository.findByIdOrNull(id)

    override fun save(componentRating: ComponentRating): ComponentRating =
            this.jpaComponentRatingRepository.save(componentRating)
}