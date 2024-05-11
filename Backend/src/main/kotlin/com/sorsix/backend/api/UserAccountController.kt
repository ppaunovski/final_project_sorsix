package com.sorsix.backend.api

import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.service.UserAccountService
import org.apache.tomcat.util.http.parser.Authorization
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserAccountController(val userAccountService: UserAccountService) {
    @GetMapping
    fun findUser(@RequestHeader(name = "Authorization") authorizationHeader: String, auth: Authentication) =
        userAccountService.findUserAccountByJWT(authorizationHeader, auth)
    @GetMapping("/{id}")
    fun findUserAccountById(@PathVariable id: Long) = userAccountService.findUserAccountById(id)
    @PostMapping
    fun saveUserAccount(@RequestBody userAccount: UserAccount) = userAccountService.saveUserAccount(userAccount)
    @DeleteMapping("/{id}")
    fun deleteUserAccountById(@PathVariable id: Long) = userAccountService.deleteUserAccountById(id)
}