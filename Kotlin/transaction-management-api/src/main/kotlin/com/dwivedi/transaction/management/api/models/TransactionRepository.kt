package com.dwivedi.transaction.management.api.models

import org.springframework.data.repository.CrudRepository

interface TransactionRepository : CrudRepository<Transaction, Int>
