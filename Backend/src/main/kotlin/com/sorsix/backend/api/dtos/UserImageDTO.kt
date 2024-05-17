package com.sorsix.backend.api.dtos

data class UserImageDTO(
    val id: Long,
    val image: ByteArray,
    val type: String,
    val userId: Long
)
