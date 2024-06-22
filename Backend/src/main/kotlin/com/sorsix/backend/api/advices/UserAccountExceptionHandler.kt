package com.sorsix.backend.api.advices

import com.sorsix.backend.api.dtos.ErrorDTO
import com.sorsix.backend.service.exceptions.InvalidCredentialsException
import com.sorsix.backend.service.exceptions.UnauthorizedAccessException
import com.sorsix.backend.service.exceptions.UserAccountNotFoundException
import com.sorsix.backend.service.exceptions.UserImageNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class UserAccountExceptionHandler {
    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentialsException(e: InvalidCredentialsException): ResponseEntity<ErrorDTO> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST.value())
            .body(
                ErrorDTO(
                    e.message,
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name,
                    LocalDateTime.now(),
                ),
            )
    }

    @ExceptionHandler(UserAccountNotFoundException::class)
    fun handleUserAccountNotFoundException(e: UserAccountNotFoundException): ResponseEntity<ErrorDTO> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND.value())
            .body(
                ErrorDTO(
                    e.message,
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.name,
                    LocalDateTime.now(),
                ),
            )
    }

    @ExceptionHandler(UnauthorizedAccessException::class)
    fun handleUnauthorizedAccessException(e: UnauthorizedAccessException): ResponseEntity<ErrorDTO> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED.value())
            .body(
                ErrorDTO(
                    e.message,
                    HttpStatus.UNAUTHORIZED.value(),
                    HttpStatus.UNAUTHORIZED.name,
                    LocalDateTime.now(),
                ),
            )
    }

    @ExceptionHandler(UserImageNotFoundException::class)
    fun handleUserImageNotFoundException(e: UserImageNotFoundException): ResponseEntity<ErrorDTO> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND.value())
            .body(
                ErrorDTO(
                    e.message,
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.name,
                    LocalDateTime.now(),
                ),
            )
    }
}
