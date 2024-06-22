package com.sorsix.backend.service.exceptions

class UserAccountNotFoundException(
    message: String,
) : RuntimeException(message)
