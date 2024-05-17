package com.sorsix.backend.config.oauth2

import com.sorsix.backend.config.JwtService
import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.repository.user_account_repository.UserAccountRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class CustomOAUth2SuccessHandler(
    private val jwtService: JwtService,
    private val userAccountRepository: UserAccountRepository
) : SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        var token = ""
        if (authentication != null) {

            token = jwtService.generateToken(userDetails = authentication.principal as UserDetails)
            response?.addHeader("Authorization", "Bearer $token")
        }
        redirectStrategy.sendRedirect(request, response, "http://localhost:4200/login-callback?token=$token")

    }


}