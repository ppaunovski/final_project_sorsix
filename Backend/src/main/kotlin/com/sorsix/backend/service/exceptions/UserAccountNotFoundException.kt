package com.sorsix.backend.service.exceptions

class UserAccountNotFoundException(id: Long) : RuntimeException("User account with id $id not found"){
}