package com.sorsix.backend.config

import com.sorsix.backend.repository.user_account_repository.UserAccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ApplicationConfig(
    private val userAccountRepository: UserAccountRepository
) {
    @Bean
    fun userDetailsService(): UserDetailsService{
        return UserDetailsService { email ->
            userAccountRepository.findByEmail(email)
        }
    }

    @Bean
    fun encoder(): PasswordEncoder =
//        BCryptPasswordEncoder()
        NoOpPasswordEncoder.getInstance()

    @Bean
    fun authenticationProvider(userAccountRepository: UserAccountRepository): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetailsService())
        provider.setPasswordEncoder(encoder())
        return provider
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }
}