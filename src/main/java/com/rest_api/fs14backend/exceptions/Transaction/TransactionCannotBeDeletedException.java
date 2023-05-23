package com.rest_api.fs14backend.exceptions.Transaction;


public class TransactionCannotBeDeletedException extends RuntimeException {
    public TransactionCannotBeDeletedException() {
        super("Cannot Delete Author!");
    }
}