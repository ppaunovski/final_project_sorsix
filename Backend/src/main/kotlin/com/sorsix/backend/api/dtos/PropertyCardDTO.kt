package com.sorsix.backend.api.dtos

data class PropertyCardDTO(
    val id: Long,


    val type: String,
    val cityName: String,
    val name: String,

    val address: String,

    val averageRating: Double,

    val description: String,

    val pricePerNight: Double,

    val image: PropertyImageDTO,
    val longitude: Double,
    val latitude: Double
)
