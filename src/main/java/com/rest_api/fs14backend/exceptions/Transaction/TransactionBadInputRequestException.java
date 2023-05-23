package com.rest_api.fs14backend.exceptions.Transaction;


public class TransactionBadInputRequestException extends RuntimeException {
    public TransactionBadInputRequestException() {
        super("Transaction cannot be created/updated! please check input");
    }
}
