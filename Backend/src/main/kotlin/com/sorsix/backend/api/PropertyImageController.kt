package com.sorsix.backend.api

import com.sorsix.backend.api.requests.PropertyImageRequest
import com.sorsix.backend.service.PropertyImagesService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/property-image")
class PropertyImageController(private val propertyImagesService: PropertyImagesService) {
    @GetMapping("/all")
    fun getAllPropertyImages() = propertyImagesService.getAll()

    @GetMapping("/property/{id}")
    fun getAllPropertyImagesForPropertyId(@PathVariable id: Long) = propertyImagesService.getAllPropertyImagesForPropertyId(id)

    @PostMapping("/{id}/save")
    fun savePropertyImage(@PathVariable id:Long,@RequestBody image: MultipartFile)  = propertyImagesService.savePropertyImage(PropertyImageRequest(id, propertyImagesService.getNextOrder(id), image.bytes, image.contentType!!))
}