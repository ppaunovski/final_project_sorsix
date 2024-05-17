package com.sorsix.backend.api.requests

import com.sorsix.backend.api.dtos.UserImageDTO

data class RegisterRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val firstName: String,
    val lastName: String,
    val image: UserImageDTO
)
