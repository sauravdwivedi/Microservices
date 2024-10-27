package com.dwivedi.transaction.management.api.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity
data class Transaction(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Int,
        val account_id: UUID,
        val ammount: Double,
)
