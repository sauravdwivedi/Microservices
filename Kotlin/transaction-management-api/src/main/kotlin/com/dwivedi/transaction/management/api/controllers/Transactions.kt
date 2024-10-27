package com.dwivedi.transaction.management.api.controllers

import com.dwivedi.transaction.management.api.models.Transaction
import com.dwivedi.transaction.management.api.models.TransactionSchema
import com.dwivedi.transaction.management.api.services.AccountService
import com.dwivedi.transaction.management.api.services.TransactionService
import java.net.URI
import java.time.LocalDateTime
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/transactions")
class Transactions(
        private val service: TransactionService,
        private val accountService: AccountService
) {

    @GetMapping fun getAllTransactions() = service.findTransactions()

    @GetMapping("{id}")
    fun getTransactionById(@PathVariable id: Int): ResponseEntity<Transaction> {
        val transaction = service.findTransactionById(id)

        if (transaction != null) {

            return ResponseEntity.created(URI("/${transaction.id}")).body(transaction)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found")
        }
    }

    @PostMapping
    fun postTransaction(@RequestBody payload: TransactionSchema): ResponseEntity<Transaction> {
        var account = accountService.findAccountById(payload.account)

        if (account != null) {
            account.balance += payload.amount
            var transaction = Transaction(null, payload.amount, LocalDateTime.now(), account)
            val savedTransaction = service.save(transaction)

            return ResponseEntity.created(URI("/${savedTransaction.id}")).body(savedTransaction)
        } else {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not found")
        }
    }
}
