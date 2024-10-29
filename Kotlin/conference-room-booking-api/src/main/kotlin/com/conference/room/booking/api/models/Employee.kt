package com.conference.room.booking.api.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Employee(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int?,
        val name: String,
        val email: String,
        val employer: String,
)
