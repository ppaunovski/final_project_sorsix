package com.sorsix.backend.api.advices

import com.sorsix.backend.api.dtos.ErrorDTO
import com.sorsix.backend.service.exceptions.ReviewComponentNotFound
import com.sorsix.backend.service.exceptions.UserReviewNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class UserReviewExceptionHandler {
    @ExceptionHandler(UserReviewNotFoundException::class)
    fun handleUserReviewNotFoundException(e: UserReviewNotFoundException): ResponseEntity<ErrorDTO> {
        return ResponseEntity
            .status(404)
            .body(
                ErrorDTO(
                    e.message,
                    404,
                    "Not Found",
                    LocalDateTime.now(),
                ),
            )
    }

    @ExceptionHandler(ReviewComponentNotFound::class)
    fun handleReviewComponentNotFound(e: ReviewComponentNotFound): ResponseEntity<ErrorDTO> {
        return ResponseEntity
            .status(404)
            .body(
                ErrorDTO(
                    e.message,
                    404,
                    "Not Found",
                    LocalDateTime.now(),
                ),
            )
    }
}
