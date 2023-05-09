package com.rest_api.fs14backend.transaction;

import java.util.List;
import java.util.UUID;
public interface TransactionService {

    void borrowBook(BorrowDto borrowDto);
    void returnBook(UUID transactionId);
    List<Transaction> findAllByUserId(UUID userId);

    List<Transaction> findAll();

}
