package com.sorsix.backend.repository.rating

import com.sorsix.backend.api.dtos.AverageComponentRatingDTO
import com.sorsix.backend.domain.entities.ComponentRating
import com.sorsix.backend.domain.entities.UserReview
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface JpaComponentRatingRepository : JpaRepository<ComponentRating, Long> {
    fun findAllByUserReview(userReview: UserReview): List<ComponentRating>

    @Query("SELECT AVG(cr.rating) FROM ComponentRating cr WHERE cr.userReview = :userReview")
    fun averageRatingByUserReview(userReview: UserReview): Double

    //    @Query("select new com.sorsix.backend.api.dtos.AverageComponentRatingDTO(cr.reviewComponent.rcComponentName, cr.rating) " +
//            "from ComponentRating cr ")
    @Query(
        "SELECT new com.sorsix.backend.api.dtos.AverageComponentRatingDTO(cr.reviewComponent.rcComponentName, AVG(cr.rating), cr.reviewComponent.icon) " +
            "FROM ReviewComponent rc " +
            "JOIN  ComponentRating cr on cr.reviewComponent = rc " +
            "JOIN UserReview ur ON ur.id = cr.userReview.id " +
            "JOIN Property p ON p.id = ur.property.id " +
            "WHERE p.id = :propertyId and rc.id = :componentRating " +
            "GROUP BY cr.reviewComponent.rcComponentName, cr.reviewComponent.icon ",
    )
    fun averageRatingByPropertyAndComponentRating(
        @Param(value = "propertyId") propertyId: Long,
        @Param(value = "componentRating") componentRating: Long,
    ): AverageComponentRatingDTO?
}
