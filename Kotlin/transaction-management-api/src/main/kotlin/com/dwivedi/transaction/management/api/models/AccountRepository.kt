package com.dwivedi.transaction.management.api.models

import java.util.UUID
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, UUID>
