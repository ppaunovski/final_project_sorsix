package com.sorsix.backend.api

import com.sorsix.backend.domain.entities.City
import com.sorsix.backend.service.CityService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/city")
class CityController(
    val cityService: CityService,
) {
    @GetMapping()
    fun findAllCities() = cityService.findAllCities()

    @GetMapping("/{id}")
    fun findCityById(
        @PathVariable id: Long,
    ) = cityService.findCityById(id)

    @PostMapping
    fun saveCity(
        @RequestBody city: City,
    ) = cityService.saveCity(city)

    @DeleteMapping("/{id}")
    fun deleteCityById(
        @PathVariable id: Long,
    ) = cityService.deleteCityById(id)
}
