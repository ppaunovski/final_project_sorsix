package com.sorsix.backend.repository.city

import com.sorsix.backend.domain.entities.City

interface CityRepository {
    fun findCityByName(name: String): City

    fun findCityById(id: Long): City

    fun findAllCities(): List<City>

    fun saveCity(city: City): City

    fun deleteCityById(id: Long)

    fun updateCity(city: City): City
}
