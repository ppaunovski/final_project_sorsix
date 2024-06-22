package com.sorsix.backend.api.advices

import com.sorsix.backend.api.dtos.ErrorDTO
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import org.apache.tomcat.websocket.AuthenticationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class AuthExceptionHandler {
    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(e: AuthenticationException): ResponseEntity<ErrorDTO> {
        return ResponseEntity
            .status(401)
            .body(
                ErrorDTO(
                    e.message,
                    401,
                    "Unauthorized",
                    LocalDateTime.now(),
                ),
            )
    }

    @ExceptionHandler(JwtException::class)
    fun handleJwtException(e: JwtException): ResponseEntity<ErrorDTO> {
        return ResponseEntity
            .status(401)
            .body(
                ErrorDTO(
                    e.message,
                    401,
                    "Unauthorized",
                    LocalDateTime.now(),
                ),
            )
    }

    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwtException(e: ExpiredJwtException): ResponseEntity<ErrorDTO> {
        return ResponseEntity
            .status(401)
            .body(
                ErrorDTO(
                    e.message,
                    401,
                    "Unauthorized",
                    LocalDateTime.now(),
                ),
            )
    }
}
