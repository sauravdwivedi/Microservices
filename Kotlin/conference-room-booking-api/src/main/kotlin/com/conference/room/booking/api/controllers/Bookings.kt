package com.conference.room.booking.api.controllers

import com.conference.room.booking.api.models.Booking
import com.conference.room.booking.api.models.BookingCancelSchema
import com.conference.room.booking.api.models.BookingSchema
import com.conference.room.booking.api.services.BookingService
import com.conference.room.booking.api.services.EmployeeService
import java.net.URI
import java.time.LocalDateTime
import java.time.temporal.ChronoField
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/bookings")
class Bookings(private val service: BookingService, private val employeeService: EmployeeService) {

    @GetMapping("/unavailability")
    fun getUnavailability(): List<Map<String, LocalDateTime>> {
        val bookings = service.findBookings()
        var response = mutableListOf<Map<String, LocalDateTime>>()

        var unavailability: Map<String, LocalDateTime> = mapOf()

        for (booking in bookings) {
            if (booking.status == "confirmed") {
                unavailability =
                        unavailability +
                                Pair("from", booking.checkIn) +
                                Pair("to", booking.checkOut)
                response.add(unavailability)
            }
        }

        return response
    }

    @GetMapping fun getAllBookings() = service.findBookings()

    @GetMapping("{id}")
    fun getBookingById(@PathVariable id: Int): ResponseEntity<Booking> {
        val booking = service.findBookingById(id)

        if (booking != null) {

            return ResponseEntity.created(URI("/${booking.id}")).body(booking)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found")
        }
    }

    @PostMapping
    fun postBooking(@RequestBody payload: BookingSchema): ResponseEntity<Booking> {
        val employee = employeeService.findEmployeeById(payload.employeeId)
        var confirmedBookings = 0

        for (booking in service.findBookings()) {
            if (booking.status == "confirmed") {
                ++confirmedBookings
            }
        }

        if (employee == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee not found")
        }

        if (payload.checkIn >= payload.checkOut) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Check-out must be later than check-in"
            )
        }

        if (payload.checkOut.get(ChronoField.HOUR_OF_DAY) >= 18 ||
                        payload.checkIn.get(ChronoField.HOUR_OF_DAY) < 8 ||
                        payload.checkOut.get(ChronoField.DAY_OF_WEEK) > 5 ||
                        payload.checkIn.get(ChronoField.DAY_OF_WEEK) > 5
        ) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Booking is only allowed between Monday to Friday from 8:00 to 18:00"
            )
        }

        if (confirmedBookings >= 20) {
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Only 20 employees are allowed to book"
            )
        }

        var booking =
                Booking(
                        null,
                        payload.checkIn,
                        payload.checkOut,
                        "confirmed",
                        (101..102).shuffled().first(),
                        employee
                )
        val savedBooking = service.save(booking)

        return ResponseEntity.created(URI("/${savedBooking.id}")).body(savedBooking)
    }

    @PatchMapping
    fun cancelBooking(@RequestBody payload: BookingCancelSchema): ResponseEntity<Booking> {
        var booking = service.findBookingById(payload.bookingId)

        if (booking != null) {
            booking.status = "cancelled"
            val savedBooking = service.save(booking)

            return ResponseEntity.created(URI("/${savedBooking.id}")).body(savedBooking)
        } else {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking not found")
        }
    }
}
