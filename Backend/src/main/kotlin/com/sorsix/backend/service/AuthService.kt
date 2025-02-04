package com.sorsix.backend.service

import com.sorsix.backend.api.requests.AuthRequest
import com.sorsix.backend.api.requests.AuthResponse
import com.sorsix.backend.api.requests.RegisterRequest
import com.sorsix.backend.config.JwtService
import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.domain.entities.UserImage
import com.sorsix.backend.repository.users.UserAccountRepository
import com.sorsix.backend.repository.users.image.UserImageRepository
import com.sorsix.backend.service.exceptions.InvalidCredentialsException
import com.sorsix.backend.service.exceptions.UnauthorizedAccessException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService,
    private val userAccountRepository: UserAccountRepository,
    private val userImageRepository: UserImageRepository,
) {
    fun authenticate(authRequest: AuthRequest): AuthResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password,
            ),
        )
        val user = userDetailsService.loadUserByUsername(authRequest.email)

        val token = jwtService.generateToken(userDetails = user)

        return AuthResponse(token)
    }

    fun register(registerRequest: RegisterRequest): AuthResponse {
        if (this.userAccountRepository.findByEmail(registerRequest.email) != null) {
            throw InvalidCredentialsException("User with email ${registerRequest.email} already exists")
        }
        if (registerRequest.password != registerRequest.confirmPassword) {
            throw InvalidCredentialsException("Passwords do not match")
        }
        val user =
            this.userAccountRepository.save(
                UserAccount(
                    email = registerRequest.email,
                    userPassword = registerRequest.password,
                    firstName = registerRequest.firstName,
                    lastName = registerRequest.lastName,
                    dateHostStarted = null,
                    joinedDate = LocalDate.now(),
                    id = 4,
                ),
            )

        this.userImageRepository.save(
            UserImage(
                user = user,
                image = registerRequest.image.image,
                type = registerRequest.image.type,
                id = 4,
            ),
        )
        return this.authenticate(AuthRequest(user.email, user.userPassword))
    }

    fun isAuthenticated(authentication: Authentication?): Boolean {
        if (authentication == null) throw UnauthorizedAccessException("User is not authenticated")
        return true
    }
}
