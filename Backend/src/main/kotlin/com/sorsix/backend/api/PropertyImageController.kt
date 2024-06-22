package com.sorsix.backend.api

import com.sorsix.backend.api.dummy.DummyDataPopulator
import com.sorsix.backend.api.requests.PropertyImageRequest
import com.sorsix.backend.service.PropertyImagesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/property-image")
class PropertyImageController(
    private val propertyImagesService: PropertyImagesService,
    private val dummyDataPopulator: DummyDataPopulator,
) {
    @GetMapping("/all")
    fun getAllPropertyImages() = propertyImagesService.getAll()

    @GetMapping("/property/{id}")
    fun getAllPropertyImagesForPropertyId(
        @PathVariable id: Long,
    ) = propertyImagesService.getAllPropertyImagesForPropertyId(id)

    @PostMapping("/{id}/save")
    fun savePropertyImage(
        @PathVariable id: Long,
        @RequestBody image: MultipartFile,
    ) = propertyImagesService.savePropertyImage(
        PropertyImageRequest(
            id,
            propertyImagesService.getNextOrder(id),
            image.bytes,
            image.contentType!!,
        ),
    )

    @GetMapping("/populate")
    fun populatePropertyImages() = dummyDataPopulator.addPropertyImages()
}
