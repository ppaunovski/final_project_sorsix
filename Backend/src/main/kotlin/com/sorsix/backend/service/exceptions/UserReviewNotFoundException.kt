package com.sorsix.backend.service.exceptions

class UserReviewNotFoundException(id: Long) : RuntimeException("User Review with id $id not found") {
}