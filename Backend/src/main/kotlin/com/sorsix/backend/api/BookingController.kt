package com.sorsix.backend.api

import com.sorsix.backend.api.dummy.DummyDataPopulator
import com.sorsix.backend.domain.entities.Booking
import com.sorsix.backend.service.BookingService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/booking")
class BookingController(
    private val bookingService: BookingService,
    private val dummyDataPopulator: DummyDataPopulator,
) {
    @GetMapping()
    fun findAllBookings() = bookingService.findAllBookings()

    @GetMapping("/{id}")
    fun findBookingById(
        @PathVariable id: Long,
    ) = bookingService.getBookingDTOById(id)

    @PostMapping
    fun saveBooking(
        @RequestBody booking: Booking,
    ) = bookingService.saveBooking(booking)

    @DeleteMapping("/{id}")
    fun deleteBookingById(
        @PathVariable id: Long,
    ) = bookingService.deleteBookingById(id)

    @GetMapping("/user")
    fun getBookingsForUser(
        @RequestParam(name = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int,
        authentication: Authentication,
    ) = bookingService.getBookingsForUser(page, size, authentication)

    @PostMapping("/{id}/confirm")
    fun confirmBooking(
        @PathVariable id: Long,
        authentication: Authentication,
    ) = bookingService.confirmBooking(id, authentication)

    @PostMapping("/{id}/cancel")
    fun cancelBooking(
        @PathVariable id: Long,
        authentication: Authentication,
    ) = bookingService.cancelBooking(id, authentication)

    @GetMapping("/{id}/for-review")
    fun getBookingForReview(
        @PathVariable id: Long,
        authentication: Authentication,
    ) = bookingService.getBookingForReview(id, authentication)

    @GetMapping("/populate")
    fun populateBookings() = dummyDataPopulator.addDummyBookings()
}
