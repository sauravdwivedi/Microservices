package com.dwivedi.transaction.management.models;

import java.util.UUID;

public record ResponseSchemaAccount(String message, UUID account_id, double balance, Integer status) {

}