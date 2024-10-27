package com.dwivedi.transaction.management.api.services

import com.dwivedi.transaction.management.api.models.Account
import com.dwivedi.transaction.management.api.models.AccountRepository
import java.util.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AccountService(private val db: AccountRepository) {
    fun findAccounts(): List<Account> = db.findAll().toList()

    fun findAccountById(id: UUID): Account? = db.findByIdOrNull(id)

    fun save(account: Account): Account = db.save(account)
}
