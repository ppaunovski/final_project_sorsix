package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.PropertyCardDTO
import com.sorsix.backend.api.dtos.PropertyImageDTO
import com.sorsix.backend.api.dtos.UserAccountDTO
import com.sorsix.backend.domain.entities.UserAccount
import com.sorsix.backend.repository.property_images_repository.PropertyImagesRepository
import com.sorsix.backend.repository.property_repository.PropertyRepository
import com.sorsix.backend.repository.user_account_repository.UserAccountRepository
import com.sorsix.backend.repository.user_review_repository.UserReviewRepository
import com.sorsix.backend.service.exceptions.UnauthorizedAccessException
import com.sorsix.backend.service.exceptions.UserAccountNotFoundException
import org.apache.tomcat.util.http.parser.Authorization
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.Unauthorized

@Service
class UserAccountService(
    private val userAccountRepository: UserAccountRepository,
    private val propertyRepository: PropertyRepository,
    private val propertyImagesRepository: PropertyImagesRepository,
    private val reviewRepository: UserReviewRepository,
    private val componentRatingService: ComponentRatingService
) {
    fun findAllUserAccounts() =
        userAccountRepository.findAll().map { mapUserAccountToDTO(it) }

    fun findUserAccountById(id: Long) =
        userAccountRepository.findById(id) ?: throw UserAccountNotFoundException("User account with id $id not found")

    fun getUserAccountDTOById(id: Long) =
        userAccountRepository.findById(id)?.let { mapUserAccountToDTO(it) }
            ?: throw UserAccountNotFoundException("User account with id $id not found")

    fun saveUserAccount(userAccount: UserAccount) = userAccountRepository.save(userAccount)
    fun deleteUserAccountById(id: Long) = userAccountRepository.deleteById(id)

    fun mapUserAccountToDTO(userAccount: UserAccount) =
        UserAccountDTO(
            id = userAccount.id,
            firstName = userAccount.firstName,
            lastName = userAccount.lastName,
            email = userAccount.email,
            joinedDate = userAccount.joinedDate,
            dateHostStarted = userAccount.dateHostStarted
        )

    fun findUserAccountByJWT(authorizationHeader: String, auth: Authentication): UserAccountDTO {
        val user = userAccountRepository.findByEmail(auth.name)
        return user?.let { mapUserAccountToDTO(it) }
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

        return this.propertyRepository.findAllByHost(host).map { property ->
            PropertyCardDTO(
                id = property.id,
                cityName = property.city.name,
                address = property.address,
                averageRating = this.reviewRepository
                    .findAllByProperty(property)
                    .map {
                        this.componentRatingService
                            .findAverageComponentRatingForUserReview(it.id)
                    }.average(),
                description = property.description,
                pricePerNight = property.nightlyPrice,
                images = this.propertyImagesRepository.findAllByPropertyId(property.id).map {
                    PropertyImageDTO(
                        id = it.id,
                        propertyId = it.property.id,
                        order = it.order,
                        imageByteArray = it.image,
                        type = it.type
                    )
                },
                type = property.propertyType.typeName,
                name = property.name
            )
        }


    }

}