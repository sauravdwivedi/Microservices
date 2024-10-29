package com.conference.room.booking.api.models

import java.time.LocalDateTime

data class BookingSchema(
        val employeeId: Int,
        val checkIn: LocalDateTime,
        val checkOut: LocalDateTime,
)
