package com.dwivedi.transaction.management.api.controllers

import com.dwivedi.transaction.management.api.models.Account
import com.dwivedi.transaction.management.api.models.AccountSchema
import com.dwivedi.transaction.management.api.services.AccountService
import java.net.URI
import java.util.UUID
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
@RequestMapping("/api/v1/accounts")
class Accounts(private val service: AccountService) {
    @GetMapping fun getAllAccounts() = service.findAccounts()

    @GetMapping("{id}")
    fun getAccountById(@PathVariable id: UUID): ResponseEntity<Account> {
        val account = service.findAccountById(id)

        if (account != null) {

            return ResponseEntity.created(URI("/${account.id}")).body(account)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found")
        }
    }

    @PostMapping
    fun postAccount(@RequestBody payload: AccountSchema): ResponseEntity<Account> {
        val accountId = UUID.randomUUID()
        val savedAccount = service.save(Account(accountId, payload.balance))

        return ResponseEntity.created(URI("/${savedAccount.id}")).body(savedAccount)
    }
}
