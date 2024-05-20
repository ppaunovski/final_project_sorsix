package com.sorsix.backend.api

import com.sorsix.backend.service.FavoriteService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/favorite")
class FavoriteController(private val favoriteService: FavoriteService) {
    @GetMapping("/all")
    fun getAllFavorites(authentication: Authentication?) = favoriteService.findAllFavoritesForUser(authentication)

    @DeleteMapping("/delete/{propertyId}")
    fun deleteFavorite(authentication: Authentication?, @PathVariable propertyId: Long) = favoriteService.deleteFavoriteByUserIdAndPropertyId(authentication, propertyId)

    @PostMapping("/save/{propertyId}")
    fun saveFavorite(authentication: Authentication?, @PathVariable propertyId: Long) = favoriteService.saveFavorite(authentication, propertyId)

    @GetMapping("/isFavorite/{propertyId}")
    fun isFavorite(authentication: Authentication?, @PathVariable propertyId: Long) = favoriteService.isFavorite(authentication, propertyId)

}