package com.sorsix.backend.repository.user_image_repository

import com.sorsix.backend.domain.entities.UserImage
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserImageRepository: JpaRepository<UserImage, Long> {
    fun findByUserId(userId: Long): UserImage?
}