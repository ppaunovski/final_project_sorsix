package com.sorsix.backend.repository.user_account_repository

import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.stereotype.Repository

@Repository
class UserAccountRepositoryImpl(private val userAccountRepository: JpaUserAccountRepository): UserAccountRepository {
    override fun findAll(): List<UserAccount> =
         userAccountRepository.findAll()


    override fun findById(id: Long): UserAccount? =
         userAccountRepository.findById(id).orElse(null)


    override fun save(userAccount: UserAccount): UserAccount =
         userAccountRepository.save(userAccount)


    override fun deleteById(id: Long) =
        userAccountRepository.deleteById(id)

}