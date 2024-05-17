package com.sorsix.backend.repository.user_image_repository

import com.sorsix.backend.domain.entities.UserImage

interface UserImageRepository {
    fun findByUserId(userId: Long): UserImage
    fun save(userImage: UserImage): UserImage
    fun findById(id: Long): UserImage
    fun findAll(): List<UserImage>

}