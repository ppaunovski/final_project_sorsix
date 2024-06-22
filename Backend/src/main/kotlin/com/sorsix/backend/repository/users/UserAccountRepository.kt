package com.sorsix.backend.repository.users

import com.sorsix.backend.api.dtos.ProfitsPerPropertyDTO
import com.sorsix.backend.domain.entities.UserAccount

interface UserAccountRepository {
    fun findAll(): List<UserAccount>

    fun findById(id: Long): UserAccount?

    fun save(userAccount: UserAccount): UserAccount

    fun deleteById(id: Long)

    fun findByEmail(email: String): UserAccount?

    fun getProfitsPerProperty(userAccount: UserAccount): List<ProfitsPerPropertyDTO>
}
