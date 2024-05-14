package com.sorsix.backend.api.advices

import com.sorsix.backend.api.dtos.ErrorDTO
import com.sorsix.backend.service.exceptions.PropertyCapacityException
import com.sorsix.backend.service.exceptions.PropertyNotAvailableException
import com.sorsix.backend.service.exceptions.PropertyNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class PropertyExceptionHandler {
    @ExceptionHandler(PropertyNotFoundException::class)
    fun handlePropertyNotFoundException(e: PropertyNotFoundException): ResponseEntity<ErrorDTO> {

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ErrorDTO(
                    e.message,
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.reasonPhrase,
                    LocalDateTime.now()
                )
            )
    }

    @ExceptionHandler(PropertyNotAvailableException::class)
    fun handlePropertyNotAvailableException(e: PropertyNotAvailableException): ResponseEntity<ErrorDTO> {

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ErrorDTO(
                    e.message,
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.reasonPhrase,
                    LocalDateTime.now()
                )
            )
    }

    @ExceptionHandler(PropertyCapacityException::class)
    fun handlePropertyCapacityException(e: PropertyCapacityException): ResponseEntity<ErrorDTO> {

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorDTO(
                    e.message,
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.reasonPhrase,
                    LocalDateTime.now()
                )
            )
    }
}