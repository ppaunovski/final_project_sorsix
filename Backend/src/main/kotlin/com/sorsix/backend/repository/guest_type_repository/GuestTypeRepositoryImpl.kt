package com.sorsix.backend.repository.guest_type_repository

import com.sorsix.backend.domain.entities.GuestType
import org.springframework.data.repository.findByIdOrNull

class GuestTypeRepositoryImpl(
        private val jpaGuestTypeRepository: JpaGuestTypeRepository
) : GuestTypeRepository {
    override fun findAll(): List<GuestType> =
            this.jpaGuestTypeRepository.findAll()

    override fun findById(id: Long): GuestType? =
            this.jpaGuestTypeRepository.findByIdOrNull(id)

    override fun save(guestType: GuestType): GuestType =
            this.jpaGuestTypeRepository.save(guestType)
}