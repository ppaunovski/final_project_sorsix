package com.sorsix.backend.api.dtos

import com.sorsix.backend.domain.entities.City
import com.sorsix.backend.domain.entities.PropertyType

data class PropertyDTO(
    val id: Long,
    var nightlyPrice: Double,
    var name: String,
    var guests: Int,
    var beds: Int,
    var bedrooms: Int,
    var bathrooms: Int,
    var isGuestFavorite: Boolean,
    var description: String,
    var address: String,
    var longitude: Double,
    var latitude: Double,
    var host: UserAccountDTO,
    var city: City,
    var propertyType: PropertyType,
    var images: List<PropertyImageDTO>,
)
