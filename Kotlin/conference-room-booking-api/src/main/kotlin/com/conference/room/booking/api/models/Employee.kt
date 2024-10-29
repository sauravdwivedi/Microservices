package com.conference.room.booking.api.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

@Entity
data class Employee(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int?,
        @field:Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        val name: String,
        @field:Email(message = "Email must be valid") val email: String,
        val employer: String,
)
