package com.sorsix.backend.api

import com.sorsix.backend.api.dtos.ComponentRatingDTO
import com.sorsix.backend.api.dummy.DummyDataPopulator
import com.sorsix.backend.api.requests.ReviewRequest
import com.sorsix.backend.domain.entities.ReviewComponent
import com.sorsix.backend.service.ComponentRatingService
import com.sorsix.backend.service.ReviewComponentService
import com.sorsix.backend.service.ReviewService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reviews")
class ReviewController(
    private val reviewService: ReviewService,
    private val componentRatingService: ComponentRatingService,
    private val reviewComponentService: ReviewComponentService,
    private val dummyDataPopulator: DummyDataPopulator,
) {
    @GetMapping("/{id}/components")
    fun getAllComponentRatingsForReview(
        @PathVariable id: Long,
    ): List<ComponentRatingDTO> = this.componentRatingService.findAllComponentRatingsForUserReview(id)

    @GetMapping("/{id}/components/average")
    fun getAverageComponentRatingForReview(
        @PathVariable id: Long,
    ): Double = String.format("%.2f", this.componentRatingService.findAverageComponentRatingForUserReview(id)).toDouble()

    @GetMapping("/components")
    fun getReviewComponents(): List<ReviewComponent> = this.reviewComponentService.getAllReviewComponents()

    @PostMapping()
    fun saveReview(
        @RequestBody review: ReviewRequest,
        authentication: Authentication?,
    ) = this.reviewService.saveReview(review, authentication)

    @GetMapping("/populate")
    fun populateReviews() = this.dummyDataPopulator.addDummyReviews()
}
