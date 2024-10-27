package com.dwivedi.transaction.management.api.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity
data class Account(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID,
        val balance: Double,
)
