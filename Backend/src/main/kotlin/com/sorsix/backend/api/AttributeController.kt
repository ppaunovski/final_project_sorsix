package com.sorsix.backend.api

import com.sorsix.backend.repository.attribute_repository.AttributeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/attributes")
class AttributeController(private val attributeRepository: AttributeRepository) {
    @GetMapping
    fun getAllAttributes() = attributeRepository.findAll()
}