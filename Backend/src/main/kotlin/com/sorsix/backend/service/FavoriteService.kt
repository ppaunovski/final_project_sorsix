package com.sorsix.backend.service

import com.sorsix.backend.api.dtos.PropertyCardDTO
import com.sorsix.backend.domain.entities.Favorite
import com.sorsix.backend.repository.favorite_repository.FavoriteRepository
import com.sorsix.backend.repository.property_repository.PropertyRepository
import com.sorsix.backend.repository.user_account_repository.UserAccountRepository
import com.sorsix.backend.service.exceptions.PropertyNotFoundException
import com.sorsix.backend.service.exceptions.UnauthorizedAccessException
import com.sorsix.backend.service.exceptions.UserAccountNotFoundException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class FavoriteService(private val favoriteRepository: FavoriteRepository,
                      private val userAccountRepository: UserAccountRepository,
    private val propertyRepository: PropertyRepository,
    private val dtoMapperService: ClassToDTOMapperService
){


    fun findAllFavoritesForUser(authenticate: Authentication?): List<PropertyCardDTO>{
        if(authenticate == null) throw UnauthorizedAccessException("User not authenticated")
        val user = userAccountRepository.findByEmail(authenticate.name)?.id
        val favorites = user?.let { this.favoriteRepository.findByUserId(it) }
                ?: throw UserAccountNotFoundException("User not found")
         val propertyList: List<PropertyCardDTO> = favorites.map { favorite ->
            val property = favorite.property
            dtoMapperService.mapPropertyToPropertyCardDTO(property)

        }
        return propertyList
    }

    fun deleteFavoriteByUserIdAndPropertyId(authenticate: Authentication?, propertyId: Long):Long{
        if(authenticate == null) throw UnauthorizedAccessException("User not authenticated")
        val user = userAccountRepository.findByEmail(authenticate.name)?.id
            ?: throw UserAccountNotFoundException("User not found")
        this.favoriteRepository.deleteByUserIdAndPropertyId(user, propertyId)
        return propertyId
    }
    fun saveFavorite(authenticate: Authentication?,propertyId: Long){
        if(authenticate == null) throw UnauthorizedAccessException("User not authenticated")
        val user = userAccountRepository.findByEmail(authenticate.name)
            ?: throw UserAccountNotFoundException("User not found")
        val property = propertyRepository.findById(propertyId) ?: throw PropertyNotFoundException("Property not found")
        val favorite = Favorite(id = 0, user = user, property = property)
        this.favoriteRepository.save(favorite)
    }
    fun isFavorite(authenticate: Authentication?, propertyId: Long): Boolean{
        if(authenticate == null) throw UnauthorizedAccessException("User not authenticated")
        val user = userAccountRepository.findByEmail(authenticate.name)?.id
            ?: throw UserAccountNotFoundException("User not found")
        return this.favoriteRepository.existsByUserIdAndPropertyId(user, propertyId)
    }
}