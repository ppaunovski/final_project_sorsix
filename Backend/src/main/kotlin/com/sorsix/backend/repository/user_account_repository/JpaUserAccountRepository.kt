package com.sorsix.backend.repository.user_account_repository

import com.sorsix.backend.domain.entities.UserAccount
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserAccountRepository: JpaRepository<UserAccount, Long> {

}