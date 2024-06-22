package com.sorsix.backend.config.oauth2

abstract class OAuth2UserDetails(
    var attributes: Map<String, Any>,
) {
    abstract fun getEmail(): String

    abstract fun getName(): String
}
