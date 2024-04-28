package com.sorsix.backend.api

import com.sorsix.backend.api.dtos.ComponentRatingDTO
import com.sorsix.backend.domain.entities.ComponentRating
import com.sorsix.backend.service.ComponentRatingService
import com.sorsix.backend.service.ReviewService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reviews")
class ReviewController(
    private val reviewService: ReviewService,
    private val componentRatingService: ComponentRatingService
) {
    @GetMapping("/{id}/components")
    fun getAllComponentRatingsForReview(@PathVariable id: Long): List<ComponentRatingDTO> =
        this.componentRatingService.findAllComponentRatingsForUserReview(id)
}