package com.sorsix.backend.api.dtos

import jakarta.persistence.Column

data class AverageComponentRatingDTO(
    @Column(name = "rc_component_name")
    val name: String,
    @Column(name = "avg_rating")
    val averageRating: Double,
    @Column(name = "rc_icon")
    val icon: String,
)
