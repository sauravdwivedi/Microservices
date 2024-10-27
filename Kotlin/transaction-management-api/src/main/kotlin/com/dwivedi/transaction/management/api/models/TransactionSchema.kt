package com.dwivedi.transaction.management.api.models

import java.util.UUID

data class TransactionSchema(
        val account: UUID,
        val amount: Double,
)
