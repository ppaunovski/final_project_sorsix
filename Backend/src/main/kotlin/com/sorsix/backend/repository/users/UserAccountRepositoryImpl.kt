package com.sorsix.backend.repository.users

import com.sorsix.backend.api.dtos.ProfitsPerPropertyDTO
import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.stereotype.Repository

@Repository
class UserAccountRepositoryImpl(
    private val userAccountRepository: JpaUserAccountRepository,
) : UserAccountRepository {
    override fun findAll(): List<UserAccount> = userAccountRepository.findAll()

    override fun findById(id: Long): UserAccount? = userAccountRepository.findById(id).orElse(null)

    override fun save(userAccount: UserAccount): UserAccount = userAccountRepository.save(userAccount)

    override fun deleteById(id: Long) = userAccountRepository.deleteById(id)

    override fun findByEmail(email: String): UserAccount? = this.userAccountRepository.findByEmail(email)

    override fun getProfitsPerProperty(userAccount: UserAccount): List<ProfitsPerPropertyDTO> =
        this.userAccountRepository.getProfitsPerProperty(userAccount)
}
