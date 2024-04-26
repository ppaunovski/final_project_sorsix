package com.sorsix.backend.api

import com.sorsix.backend.domain.entities.City
import com.sorsix.backend.service.CityService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/city")
class CityController(val cityService: CityService) {
    @GetMapping
    fun findAllCities() = cityService.findAllCities()
    @GetMapping("/{id}")
    fun findCityById(@PathVariable id: Long) = cityService.findCityById(id)
    @PostMapping
    fun saveCity(@RequestBody city: City) = cityService.saveCity(city)
    @DeleteMapping("/{id}")
    fun deleteCityById(@PathVariable id: Long) = cityService.deleteCityById(id)

}