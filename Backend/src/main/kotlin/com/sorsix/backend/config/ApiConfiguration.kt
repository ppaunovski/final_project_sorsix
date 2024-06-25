package com.sorsix.backend.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "api")
class ApiConfiguration(
    var host: String = "",
)
