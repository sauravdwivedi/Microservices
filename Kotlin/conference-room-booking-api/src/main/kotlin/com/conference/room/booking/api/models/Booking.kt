package com.conference.room.booking.api.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class Booking(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int?,
        val checkIn: LocalDateTime,
        val checkOut: LocalDateTime,
        var status: String,
        val room: Int,
        @ManyToOne val employee: Employee,
)
