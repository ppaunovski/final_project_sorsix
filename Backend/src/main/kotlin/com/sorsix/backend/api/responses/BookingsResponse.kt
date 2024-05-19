package com.sorsix.backend.api.responses

import com.sorsix.backend.api.dtos.BookingDTO

data class BookingsResponse (
    val content: List<BookingDTO>,
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int,
    val last: Boolean
)