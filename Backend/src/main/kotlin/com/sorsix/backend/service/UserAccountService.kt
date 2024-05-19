package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.ProfitsPerPropertyDTO
import com.sorsix.backend.api.dtos.PropertyCardDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.api.dtos.UserImageDTO
import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.repository.property_images_repository.PropertyImagesRepository
import com.sorsix.backend.repository.property_repository.PropertyRepository
import com.sorsix.backend.repository.user_account_repository.UserAccountRepository
import com.sorsix.backend.repository.user_image_repository.UserImageRepository
import com.sorsix.backend.repository.user_review_repository.UserReviewRepository
import com.sorsix.backend.service.exceptions.UnauthorizedAccessException
import com.sorsix.backend.service.exceptions.UserAccountNotFoundException
import com.sorsix.backend.service.exceptions.UserImageNotFoundException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class UserAccountService(
    private val userAccountRepository: UserAccountRepository,
    private val propertyRepository: PropertyRepository,
    private val propertyImagesRepository: PropertyImagesRepository,
    private val reviewRepository: UserReviewRepository,
    private val componentRatingService: ComponentRatingService,
    private val dtoMapperService: ClassToDTOMapperService,
    private val userImageRepository: UserImageRepository
) {
    fun findAllUserAccounts() =
        userAccountRepository.findAll().map { dtoMapperService.mapUserAccountToDTO(it) }

    fun findUserAccountById(id: Long) =
        userAccountRepository.findById(id) ?: throw UserAccountNotFoundException("User account with id $id not found")

    fun getUserAccountDTOById(id: Long) =
        userAccountRepository.findById(id)?.let { dtoMapperService.mapUserAccountToDTO(it) }
            ?: throw UserAccountNotFoundException("User account with id $id not found")

    fun saveUserAccount(userAccount: UserAccount) = userAccountRepository.save(userAccount)
    fun deleteUserAccountById(id: Long) = userAccountRepository.deleteById(id)


    fun findUserAccountByJWT(authorizationHeader: String, auth: Authentication): UserAccountDTO {
        val user = userAccountRepository.findByEmail(auth.name)
        return user?.let { dtoMapperService.mapUserAccountToDTO(it) }
            ?: throw UserAccountNotFoundException("User account with email ${auth.name} not found")
    }

    fun findUserByEmail(email: String) = userAccountRepository.findByEmail(email) ?: throw UserAccountNotFoundException(
        "User account with email $email not found"
    )

    fun findPropertiesForUser(id: Long, auth: Authentication): List<PropertyCardDTO> {
        val host = this.userAccountRepository.findById(id)
            ?: throw UserAccountNotFoundException("User account with id $id not found")
        if (host.email != auth.name) {
            throw UnauthorizedAccessException("You are not authorized to view this user's properties")
        }

        return this.propertyRepository.findAllByHost(host).map { dtoMapperService.mapPropertyToPropertyCardDTO(it) }
    }

    fun findImageForUser(id: Long): UserImageDTO {
        val host = this.userAccountRepository.findById(id)
            ?: throw UserAccountNotFoundException("User account with id $id not found")

        return this.userImageRepository.findByUserId(host.id)?.let { dtoMapperService.mapUserImageToDTO(it) }
            ?: throw UserImageNotFoundException("User image for user with id $id not found")
    }

    fun getProfitsPerProperty(auth: Authentication?): List<ProfitsPerPropertyDTO> {
        if (auth == null)
            throw UnauthorizedAccessException("You are not authorized to view this user's profits")

        val user = userAccountRepository.findByEmail(auth.name)
            ?: throw UserAccountNotFoundException("User account with email ${auth.name} not found")
        return userAccountRepository.getProfitsPerProperty(user)
    }
}