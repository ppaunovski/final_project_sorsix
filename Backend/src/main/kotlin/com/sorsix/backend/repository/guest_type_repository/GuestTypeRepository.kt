package com.sorsix.backend.repository.guest_type_repository

import com.sorsix.backend.domain.entities.GuestType

interface GuestTypeRepository {
    fun findAll(): List<GuestType>
    fun findById(id: Long): GuestType?
    fun save(guestType: GuestType): GuestType
}