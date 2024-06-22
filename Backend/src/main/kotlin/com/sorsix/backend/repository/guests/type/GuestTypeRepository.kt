package com.sorsix.backend.repository.guests.type

import com.sorsix.backend.domain.entities.GuestType

interface GuestTypeRepository {
    fun findAll(): List<GuestType>

    fun findById(id: Long): GuestType?

    fun save(guestType: GuestType): GuestType
}
