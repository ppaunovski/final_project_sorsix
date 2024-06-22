package com.sorsix.backend.api

import com.sorsix.backend.api.dummy.DummyDataPopulator
import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.service.UserAccountService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserAccountController(
    val userAccountService: UserAccountService,
    private val dummyDataPopulator: DummyDataPopulator,
) {
    @GetMapping()
    fun findUser(
        @RequestHeader(name = "Authorization") authorizationHeader: String,
        auth: Authentication,
    ) = userAccountService.findUserAccountByJWT(authorizationHeader, auth)

    @GetMapping("/{id}")
    fun findUserAccountById(
        @PathVariable id: Long,
    ) = userAccountService.findUserAccountById(id)

    @PostMapping
    fun saveUserAccount(
        @RequestBody userAccount: UserAccount,
    ) = userAccountService.saveUserAccount(userAccount)

    @DeleteMapping("/{id}")
    fun deleteUserAccountById(
        @PathVariable id: Long,
    ) = userAccountService.deleteUserAccountById(id)

    @GetMapping("/{id}/properties")
    fun findPropertiesForUser(
        @PathVariable id: Long,
        auth: Authentication,
    ) = userAccountService.findPropertiesForUser(id, auth)

    @GetMapping("/{id}/images")
    fun findImageForUser(
        @PathVariable id: Long,
    ) = userAccountService.findImageForUser(id)

    @GetMapping("/profits")
    fun getProfitsPerProperty(auth: Authentication?) = userAccountService.getProfitsPerProperty(auth)

    @GetMapping("/encode")
    fun encodePasswords() = this.dummyDataPopulator.encodePasswordsForDummyUsers()
}
