package com.sorsix.backend.api

import com.sorsix.backend.domain.entities.UserImage
import com.sorsix.backend.repository.user_image_repository.UserImageRepository
import com.sorsix.backend.service.UserAccountService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/user-images")
class UserImageController(private val userImageRepository: UserImageRepository, private val userService: UserAccountService) {
    @PostMapping("/{id}")

    fun saveUserImage(@RequestBody file:MultipartFile, @PathVariable id: Long){
        val user = userService.findUserAccountById(id)
        val userImage = UserImage(
            id = 0,
            user = user,
            type = file.contentType!!,
            image = file.bytes,
            )
        userImageRepository.save(userImage)
        }
    }
