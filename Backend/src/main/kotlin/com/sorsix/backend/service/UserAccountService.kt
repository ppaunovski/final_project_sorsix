package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.repository.user_account_repository.UserAccountRepository
import com.sorsix.backend.service.exceptions.UserAccountNotFoundException
import org.springframework.stereotype.Service

@Service
class UserAccountService(val userAccountRepository: UserAccountRepository) {
    fun findAllUserAccounts() =
        userAccountRepository.findAll().map { mapUserAccountToDTO(it) }

    fun findUserAccountById(id: Long) = userAccountRepository.findById(id) ?: throw UserAccountNotFoundException(id)

    fun getUserAccountDTOById(id: Long) =
        userAccountRepository.findById(id)?.let { mapUserAccountToDTO(it) } ?: throw UserAccountNotFoundException(id)

    fun saveUserAccount(userAccount: UserAccount) = userAccountRepository.save(userAccount)
    fun deleteUserAccountById(id: Long) = userAccountRepository.deleteById(id)

    fun mapUserAccountToDTO(userAccount: UserAccount) =
        UserAccountDTO(
            id = userAccount.id,
            firstName = userAccount.firstName,
            lastName = userAccount.lastName,
            email = userAccount.email,
            joinedDate = userAccount.joinedDate,
            dateHostStarted = userAccount.dateHostStarted
        )
}