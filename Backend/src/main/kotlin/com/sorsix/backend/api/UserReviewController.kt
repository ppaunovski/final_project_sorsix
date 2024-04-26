package com.sorsix.backend.api

import com.sorsix.backend.domain.entities.UserReview
import com.sorsix.backend.service.UserReviewService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user-review")
class UserReviewController(val userReviewService: UserReviewService) {
    @GetMapping
    fun findAllUserReviews() = userReviewService.findAllUserReviews()
    @GetMapping("/{id}")
    fun findUserReviewById(@PathVariable id: Long) = userReviewService.findUserReviewById(id)
    @PostMapping
    fun saveUserReview(@RequestBody userReview: UserReview) = userReviewService.saveUserReview(userReview)
    @DeleteMapping("/{id}")
    fun deleteUserReviewById(@PathVariable id: Long) = userReviewService.deleteUserReviewById(id)
}