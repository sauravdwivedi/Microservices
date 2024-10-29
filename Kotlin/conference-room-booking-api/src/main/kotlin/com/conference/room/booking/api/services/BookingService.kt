package com.conference.room.booking.api.services

import com.conference.room.booking.api.models.Booking
import com.conference.room.booking.api.models.BookingRepository
import java.util.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookingService(private val db: BookingRepository) {
    fun findBookings(): List<Booking> = db.findAll().toList()

    fun findBookingById(id: Int): Booking? = db.findByIdOrNull(id)

    fun save(booking: Booking): Booking = db.save(booking)
}
