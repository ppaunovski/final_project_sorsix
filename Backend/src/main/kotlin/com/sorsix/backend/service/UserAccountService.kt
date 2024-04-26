package com.sorsix.backend.service

import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.repository.user_account_repository.UserAccountRepository
import org.springframework.stereotype.Service

@Service
class UserAccountService(val userAccountRepository: UserAccountRepository) {
    fun findAllUserAccounts() = userAccountRepository.findAll()
    fun findUserAccountById(id: Long) = userAccountRepository.findById(id)
    fun saveUserAccount(userAccount: UserAccount) = userAccountRepository.save(userAccount)
    fun deleteUserAccountById(id: Long) = userAccountRepository.deleteById(id)
}