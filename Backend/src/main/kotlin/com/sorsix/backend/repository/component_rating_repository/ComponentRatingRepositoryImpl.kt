package com.sorsix.backend.repository.component_rating_repository

import com.sorsix.backend.api.dtos.AverageComponentRatingDTO
import com.sorsix.backend.domain.entities.ComponentRating
import com.sorsix.backend.domain.entities.UserReview
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ComponentRatingRepositoryImpl(
        private val jpaComponentRatingRepository: JpaComponentRatingRepository
) : ComponentRatingRepository{
    override fun findAll(): List<ComponentRating> =
            this.jpaComponentRatingRepository.findAll()

    override fun findById(id: Long): ComponentRating? =
            this.jpaComponentRatingRepository.findByIdOrNull(id)

    override fun save(componentRating: ComponentRating): ComponentRating =
            this.jpaComponentRatingRepository.save(componentRating)

    override fun findAllByUserReview(userReview: UserReview): List<ComponentRating> =
        this.jpaComponentRatingRepository.findAllByUserReview(userReview)

    override fun averageRatingByUserReview(userReview: UserReview): Double =
        this.jpaComponentRatingRepository.averageRatingByUserReview(userReview)

    override fun averageRatingByPropertyAndComponentRating(propertyId: Long, componentRating: Long): AverageComponentRatingDTO? =
        this.jpaComponentRatingRepository.averageRatingByPropertyAndComponentRating(propertyId, componentRating)
}