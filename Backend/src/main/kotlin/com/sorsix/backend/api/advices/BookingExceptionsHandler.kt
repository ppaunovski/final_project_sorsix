package com.sorsix.backend.api.advices

import com.sorsix.backend.api.dtos.ErrorDTO
import com.sorsix.backend.service.exceptions.BookingNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class BookingExceptionsHandler {
    @ExceptionHandler(BookingNotFoundException::class)
    fun handleBookingNotFoundException(e: BookingNotFoundException): ResponseEntity<ErrorDTO> =
        ResponseEntity
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
