package com.sorsix.backend.api

import com.sorsix.backend.api.requests.AuthRequest
import com.sorsix.backend.api.requests.AuthResponse
import com.sorsix.backend.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping()
    fun authenticate(@RequestBody authRequest: AuthRequest): AuthResponse =
        authService.authenticate(authRequest)

}