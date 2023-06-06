package com.rest_api.fs14backend.exceptions.Transaction;

public class IllegalUserAccessException  extends RuntimeException{
    public IllegalUserAccessException()  {
        super("Illegal User");
    }
}
