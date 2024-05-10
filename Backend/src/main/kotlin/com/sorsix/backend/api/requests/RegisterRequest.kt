package com.sorsix.backend.api.requests

data class RegisterRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val firstName: String,
    val lastName: String
)
