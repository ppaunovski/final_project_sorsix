package com.sorsix.backend.api

import com.sorsix.backend.api.dummy.DummyDataPopulator
import com.sorsix.backend.domain.entities.UserImage
import com.sorsix.backend.repository.users.image.UserImageRepository
import com.sorsix.backend.service.UserAccountService
import com.sorsix.backend.service.UserImageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/user-images")
class UserImageController(
    private val userImageRepository: UserImageRepository,
    private val userService: UserAccountService,
    private val userImageService: UserImageService,
    private val dummyDataPopulator: DummyDataPopulator,
) {
    @PostMapping("/{id}")
    fun saveUserImage(
        @RequestBody file: MultipartFile,
        @PathVariable id: Long,
    ) {
        val user = userService.findUserAccountById(id)
        val userImage =
            UserImage(
                id = 0,
                user = user,
                type = file.contentType!!,
                image = file.bytes,
            )
        userImageRepository.save(userImage)
    }

    @GetMapping("/populate")
    fun addUserImages() = this.dummyDataPopulator.addUserImages()
}
