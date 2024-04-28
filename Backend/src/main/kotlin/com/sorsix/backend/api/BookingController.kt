package com.sorsix.backend.api

import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.service.BookingService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/booking")
class BookingController(val bookingService: BookingService) {
    @GetMapping
    fun findAllBookings() = bookingService.findAllBookings()
    @GetMapping("/{id}")
    fun findBookingById(@PathVariable id: Long) = bookingService.getBookingDTOById(id)
    @PostMapping
    fun saveBooking(@RequestBody booking: Booking) = bookingService.saveBooking(booking)
    @DeleteMapping("/{id}")
    fun deleteBookingById(@PathVariable id: Long) = bookingService.deleteBookingById(id)
}