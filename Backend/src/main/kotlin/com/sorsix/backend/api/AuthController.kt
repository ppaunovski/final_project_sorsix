package com.sorsix.backend.api

import com.sorsix.backend.api.requests.AuthRequest
import com.sorsix.backend.api.requests.AuthResponse
import com.sorsix.backend.api.requests.RegisterRequest
import com.sorsix.backend.service.AuthService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {

    @GetMapping()
    fun isAuthenticated(authentication: Authentication?) = this.authService.isAuthenticated(authentication)

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest): AuthResponse =
        authService.register(registerRequest)

    @PostMapping()
    fun authenticate(@RequestBody authRequest: AuthRequest): AuthResponse =
        authService.authenticate(authRequest)

}