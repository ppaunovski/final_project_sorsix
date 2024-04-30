package com.sorsix.backend.api.requests


data class PropertyImageRequest(
        val propertyId: Long,
        val order: Int,
        val image: ByteArray
)
