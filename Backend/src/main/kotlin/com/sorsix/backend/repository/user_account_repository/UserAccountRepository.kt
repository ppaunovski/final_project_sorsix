package com.sorsix.backend.repository.user_account_repository

import com.sorsix.backend.domain.entities.UserAccount

interface UserAccountRepository {
    fun findAll(): List<UserAccount>
    fun findById(id: Long): UserAccount?
    fun save(userAccount: UserAccount): UserAccount
    fun deleteById(id: Long)
}