package com.sorsix.backend.service.exceptions

class PropertyNotFoundException(id: Long) : RuntimeException("Property $id not found") {
}