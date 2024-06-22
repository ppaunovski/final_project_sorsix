package com.sorsix.backend.config.oauth2

data class OAuth2GoogleUser(
    val attr: Map<String, Any>,
) : OAuth2UserDetails(attr) {
    override fun getEmail(): String = attributes["email"] as String

    override fun getName(): String = attributes["name"] as String
}
