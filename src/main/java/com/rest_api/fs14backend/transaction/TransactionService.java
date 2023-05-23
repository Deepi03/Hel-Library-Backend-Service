package com.rest_api.fs14backend.transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction borrowBook(BorrowDto borrowDto, String authorization);

    void returnBook(UUID transactionId, String authorization);

    List<Transaction> findAllByUserId(UUID userId, String authorization);

    List<Transaction> findAll();

}
