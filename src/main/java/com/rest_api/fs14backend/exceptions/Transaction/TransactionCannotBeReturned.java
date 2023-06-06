package com.rest_api.fs14backend.exceptions.Transaction;

public class TransactionCannotBeReturned extends RuntimeException{
    public TransactionCannotBeReturned() {
        super("Transaction already returned!");
}}
