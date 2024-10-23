package com.dwivedi.transaction.management.models;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, UUID> {

}