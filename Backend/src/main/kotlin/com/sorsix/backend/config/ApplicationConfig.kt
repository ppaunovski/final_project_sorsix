package com.sorsix.backend.config

import com.sorsix.backend.encoder
import com.sorsix.backend.repository.users.UserAccountRepository
import com.sorsix.backend.service.UserAccountService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class ApplicationConfig(
    private val userAccountRepository: UserAccountRepository,
    private val userAccountService: UserAccountService,
) {
    @Bean
    fun userDetailsService(): UserDetailsService =
        UserDetailsService { email ->
            userAccountRepository.findByEmail(email)
        }

    @Bean
    fun authenticationProvider(userAccountRepository: UserAccountRepository): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetailsService())
        provider.setPasswordEncoder(encoder())
        return provider
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager = config.authenticationManager
}
