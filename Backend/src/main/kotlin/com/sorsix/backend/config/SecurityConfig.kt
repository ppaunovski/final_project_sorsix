package com.sorsix.backend.config

import com.sorsix.backend.config.oauth2.CustomOAUth2SuccessHandler
import com.sorsix.backend.config.oauth2.CustomOAuth2FailureHandler
import com.sorsix.backend.config.oauth2.CustomOAuth2UserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthFilter: JwtAuthFilter,
    private val authenticationProvider: AuthenticationProvider,
    private val customOAuth2UserDetailsService: CustomOAuth2UserDetailsService,
    private val customOAUth2SuccessHandler: CustomOAUth2SuccessHandler,
    private val customOAuth2FailureHandler: CustomOAuth2FailureHandler,
) {
    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthFilter: JwtAuthFilter
    ): DefaultSecurityFilterChain =
        http
            .csrf{
                it.disable()
            }
            .authorizeHttpRequests{
                it
                    .requestMatchers("/api/booking/**").authenticated()
                    .requestMatchers(HttpMethod.GET,"/api/city/**").permitAll()
                    .requestMatchers("/api/city/**").authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/properties/**").authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/properties/{id}/for-review").authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/property-image/{id}/save-file").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/property-image/**").authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/reviews/**").authenticated()
                    .anyRequest()
                    .permitAll()

            }
            .oauth2Login { oAuth2LoginConfigurer ->
                oAuth2LoginConfigurer
                    .userInfoEndpoint {
                        it
                            .userService(customOAuth2UserDetailsService)
                    }
                    .successHandler(customOAUth2SuccessHandler)
                    .failureHandler(customOAuth2FailureHandler)
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
}