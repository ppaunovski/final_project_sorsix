package com.sorsix.backend.repository.review.component

import com.sorsix.backend.domain.entities.ReviewComponent
import org.springframework.data.jpa.repository.JpaRepository

interface JpaReviewComponentRepository : JpaRepository<ReviewComponent, Long>
