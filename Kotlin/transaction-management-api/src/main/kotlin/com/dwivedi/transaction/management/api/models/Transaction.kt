package com.dwivedi.transaction.management.api.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class Transaction(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int?,
        val ammount: Double,
        val createdAt: LocalDateTime,
        @ManyToOne val account: Account,
)
