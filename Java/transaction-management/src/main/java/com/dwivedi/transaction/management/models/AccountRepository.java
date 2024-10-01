package com.dwivedi.transaction.management.models;

import java.util.UUID;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.dwivedi.transaction.management.models.Account;

public interface AccountRepository extends CrudRepository<Account, UUID> {

}