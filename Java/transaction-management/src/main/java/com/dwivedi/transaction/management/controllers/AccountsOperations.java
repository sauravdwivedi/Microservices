package com.dwivedi.transaction.management.controllers;

import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dwivedi.transaction.management.models.Account;
import com.dwivedi.transaction.management.models.AccountRepository;
import com.dwivedi.transaction.management.models.ResponseSchemaAccount;

@RestController
public class AccountsOperations {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts/")
    public @ResponseBody Iterable<Account> getAllAccounts() {

        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public @ResponseBody Optional<Account> getAccountById(@PathVariable UUID accountId) {

        return accountRepository.findById(accountId);
    }

    @PostMapping("/accounts")
    public @ResponseBody ResponseSchemaAccount createAccount(
            @RequestParam(value = "balance", defaultValue = "0.0") double balance) {
        Account newAccount = new Account();
        newAccount.setBalance(balance);
        accountRepository.save(newAccount);

        return new ResponseSchemaAccount("The account has been created", newAccount.getId(), balance, 201);
    }
}