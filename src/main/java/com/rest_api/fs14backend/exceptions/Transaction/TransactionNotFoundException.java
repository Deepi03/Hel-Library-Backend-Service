package com.rest_api.fs14backend.exceptions.Transaction;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException() {
        super("Transaction not found");
    }
}

