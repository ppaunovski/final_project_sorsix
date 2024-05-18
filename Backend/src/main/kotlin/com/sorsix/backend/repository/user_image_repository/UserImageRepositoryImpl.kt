package com.sorsix.backend.repository.user_image_repository

import com.sorsix.backend.domain.entities.UserImage
import org.springframework.stereotype.Repository

@Repository
class UserImageRepositoryImpl(private val userImageRepository: JpaUserImageRepository) : UserImageRepository {
    override fun findByUserId(userId: Long) = userImageRepository.findByUserId(userId)
    override fun save(userImage: UserImage): UserImage {
        return userImageRepository.save(userImage)
    }

    override fun findById(id: Long): UserImage {
        return userImageRepository.findById(id).orElse(null)
    }

    override fun findAll(): List<UserImage> {
        return userImageRepository.findAll()
    }

}