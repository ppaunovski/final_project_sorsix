package com.sorsix.backend.api

import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.service.UserAccountService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user-account")
class UserAccountController(val userAccountService: UserAccountService) {
    @GetMapping
    fun findAllUserAccounts() = userAccountService.findAllUserAccounts()
    @GetMapping("/{id}")
    fun findUserAccountById(@PathVariable id: Long) = userAccountService.findUserAccountById(id)
    @PostMapping
    fun saveUserAccount(@RequestBody userAccount: UserAccount) = userAccountService.saveUserAccount(userAccount)
    @DeleteMapping("/{id}")
    fun deleteUserAccountById(@PathVariable id: Long) = userAccountService.deleteUserAccountById(id)
}