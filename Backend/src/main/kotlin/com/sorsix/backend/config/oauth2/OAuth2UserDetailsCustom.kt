package com.sorsix.backend.config.oauth2

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User

data class OAuth2UserDetailsCustom(
    val id: Long,
    var email: String,
    var pass: String,
    var authorities: List<GrantedAuthority>,
    var attribs: Map<String, Any>,
    var isNonLocked: Boolean,
    var isEnable: Boolean,
    var isNonExpired: Boolean,
) : OAuth2User, UserDetails {
    override fun getName(): String {
        return id.toString()
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return attribs.toMutableMap()
    }

    override fun getPassword(): String {
        return pass
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return isNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return isNonLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return isEnable
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities.toMutableList()
    }
}
