package com.sorsix.backend.service.exceptions

class BookingNotFoundException(id: Long?) : RuntimeException("Booking with id $id not found!")
