package com.sorsix.backend.repository.guest_type_repository

import com.sorsix.backend.domain.entities.GuestType
import org.springframework.data.jpa.repository.JpaRepository

interface JpaGuestTypeRepository: JpaRepository<GuestType, Long> {
}