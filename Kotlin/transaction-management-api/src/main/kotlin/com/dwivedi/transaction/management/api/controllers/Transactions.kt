package com.dwivedi.transaction.management.api.controllers

import com.dwivedi.transaction.management.api.models.Transaction
import com.dwivedi.transaction.management.api.services.TransactionService
import java.net.URI
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transactions")
class Transactions(private val service: TransactionService) {

    @GetMapping fun getAllTransactions() = service.findTransactions()

    @GetMapping("{id}")
    fun getTransactionById(@PathVariable id: Int): ResponseEntity<Transaction> {
        val transactionFound = service.findTransactionById(id)

        return ResponseEntity.created(URI("/${transactionFound?.id}")).body(transactionFound)
    }

    @PostMapping
    fun postTransaction(@RequestBody transaction: Transaction): ResponseEntity<Transaction> {

        val savedTransaction = service.save(transaction)

        return ResponseEntity.created(URI("/${savedTransaction.id}")).body(savedTransaction)
    }
}
