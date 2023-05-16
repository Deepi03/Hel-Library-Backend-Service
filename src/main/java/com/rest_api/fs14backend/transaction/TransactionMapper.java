package com.rest_api.fs14backend.transaction;

import org.springframework.stereotype.Component;

import java.util.Date;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.user.User;

@Component
public class TransactionMapper {
    public Transaction toTransaction(User user, Book book, Date borrowDate, Date returnDate) {
        return new Transaction(user, book, borrowDate, returnDate);
    }
}
