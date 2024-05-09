package com.sorsix.backend.api.requests

data class AuthRequest (
    val email: String,
    val password: String
)