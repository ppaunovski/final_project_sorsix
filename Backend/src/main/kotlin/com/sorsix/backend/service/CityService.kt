package com.sorsix.backend.service

import com.sorsix.backend.domain.entities.City
import com.sorsix.backend.repository.city.CityRepository
import org.springframework.stereotype.Service

@Service
class CityService(
    val cityRepository: CityRepository,
) {
    fun findCityByName(name: String) = cityRepository.findCityByName(name)

    fun findCityById(id: Long) = cityRepository.findCityById(id)

    fun findAllCities() = cityRepository.findAllCities()

    fun saveCity(city: City) = cityRepository.saveCity(city)

    fun deleteCityById(id: Long) = cityRepository.deleteCityById(id)

    fun updateCity(city: City) = cityRepository.updateCity(city)
}
