package com.rest_api.fs14backend.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookRepository;
import com.rest_api.fs14backend.user.User;
import com.rest_api.fs14backend.user.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }
    @Override
    public List<Transaction> findAllByUserId(UUID userId){
        return transactionRepository.findAllByUserId(userId);
    }

    @Override
    public void borrowBook(BorrowDto borrowDto) {
        UUID userId = borrowDto.getUserId();
        User foundUser = userRepository.findById(userId).orElse(null);
        UUID bookId = borrowDto.getBookId();
        Book foundBook = bookRepository.findById(bookId).orElse(null);
        if(foundBook != null){
            if (  foundBook.isAvailable()) {
                Transaction borrow = transactionMapper.toTransaction(foundUser, foundBook, new Date(), returnDate(borrowDto.getDay().toString()));
                foundBook.setAvailable(false);
                bookRepository.save(foundBook);
                transactionRepository.save(borrow);
            } else{
                throw new RuntimeException("Book is not available");
            }
        }
    }


    @Override
    public void returnBook(UUID transactionId) {
        Transaction foundTransaction = transactionRepository.findById(transactionId).orElse(null);
        if (foundTransaction != null) {
            Book foundBook = bookRepository.findById(foundTransaction.getBook()).orElse(null);
            if(foundBook !=null){
                foundBook.setAvailable(true);
                bookRepository.save(foundBook);
        }
            transactionRepository.delete(foundTransaction);
        }
    }



    private Date convertLocalDateTimeToDateUsingTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    private Date returnDate(String days){
        Date today = new Date();
        LocalDateTime ldt = LocalDateTime.now();
        switch (days) {
            case "TEN" -> {
                return today = convertLocalDateTimeToDateUsingTimestamp(ldt.plusDays(10));
            }
            case "TWENTY" -> {
                return today = convertLocalDateTimeToDateUsingTimestamp(ldt.plusDays(20));
            }
            default -> {
                return today = convertLocalDateTimeToDateUsingTimestamp(ldt.plusDays(30));
            }
        }
    }
}
