package com.sorsix.backend.config.oauth2

import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.repository.users.UserAccountRepository
import com.sorsix.backend.service.UserImageService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CustomOAuth2UserDetailsService(
    private val userAccountRepository: UserAccountRepository,
    private val userImageService: UserImageService,
) : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val user = DefaultOAuth2UserService().loadUser(userRequest)
        val oAuth2UserDetails = OAuth2GoogleUser(user.attributes)

        val userAccount =
            userAccountRepository.findByEmail(oAuth2UserDetails.getEmail())
                ?: // create new user
                return registerOAuth2User(oAuth2UserDetails).let {
                    OAuth2UserDetailsCustom(
                        id = it.id,
                        email = it.email,
                        pass = it.userPassword,
                        authorities = mutableListOf(SimpleGrantedAuthority("USER")),
                        attribs = user.attributes,
                        isNonLocked = true,
                        isEnable = true,
                        isNonExpired = true,
                    )
                }

        return OAuth2UserDetailsCustom(
            id = userAccount.id,
            email = userAccount.email,
            pass = userAccount.userPassword,
            authorities = mutableListOf(SimpleGrantedAuthority("USER")),
            attribs = user.attributes,
            isNonLocked = true,
            isEnable = true,
            isNonExpired = true,
        )
    }

    private fun registerOAuth2User(oAuth2UserDetails: OAuth2UserDetails): UserAccount {
        // TODO: Save profile image in db
        //  Image URL is found here.
        //  Here is a link how to download image from URL in Java: https://www.baeldung.com/java-download-file
        val imageUrl = oAuth2UserDetails.attributes["picture"] as String
        val user =
            userAccountRepository.save(
                UserAccount(
                    id = 0,
                    email = oAuth2UserDetails.getEmail(),
                    firstName = oAuth2UserDetails.getName().split("\\s+".toRegex())[0],
                    lastName = oAuth2UserDetails.getName().split("\\s+".toRegex())[1],
                    joinedDate = LocalDate.now(),
                    dateHostStarted = null,
                    userPassword = "",
                ),
            )
        userImageService.saveImageFromUrl(imageUrl, user)
        return user
    }
}
