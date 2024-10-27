package com.dwivedi.transaction.management.api.services

import com.dwivedi.transaction.management.api.models.Transaction
import com.dwivedi.transaction.management.api.models.TransactionRepository
import java.util.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TransactionService(private val db: TransactionRepository) {
    fun findTransactions(): List<Transaction> = db.findAll().toList()

    fun findTransactionById(id: Int): Transaction? = db.findByIdOrNull(id)

    fun save(transaction: Transaction): Transaction = db.save(transaction)
}
