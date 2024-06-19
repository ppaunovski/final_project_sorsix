package com.sorsix.backend.api

import com.sorsix.backend.repository.guest_type_repository.GuestTypeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/guest-type")
class GuestTypeController(private val guestTypeRepository: GuestTypeRepository) {
    @GetMapping()
    fun getAllGuestTypes() = guestTypeRepository.findAll()
}