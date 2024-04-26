package com.sorsix.backend.repository.city_repository

import com.sorsix.backend.domain.entities.City
import org.springframework.stereotype.Repository

@Repository
class CityRepositoryImpl(private val cityRepository: JpaCityRepository): CityRepository{
    override fun findCityByName(name: String): City =
         cityRepository.findCitiesByName(name)


    override fun findCityById(id: Long): City =
         cityRepository.findById(id).orElse(null)


    override fun findAllCities(): List<City> =
         cityRepository.findAll()


    override fun saveCity(city: City): City =
    cityRepository.save(city)


    override fun deleteCityById(id: Long) =
        cityRepository.deleteById(id)


    override fun updateCity(city: City): City =
         cityRepository.save(city)


}