package com.sorsix.backend.api.dtos

data class PropertyCardDTO(
    val id: Long,

//    img,

    val cityName: String,

    val address: String,

    val averageRating: Double,

    val description: String,

    val pricePerNight: Double,

    val images: List<PropertyImageDTO>
)
