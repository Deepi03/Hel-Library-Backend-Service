package com.rest_api.fs14backend.transaction;


import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.exceptions.Transaction.TransactionNotFoundException;
import com.rest_api.fs14backend.exceptions.author.AuthorCannotBeDeletedException;
import com.rest_api.fs14backend.exceptions.author.AuthorNotFoundException;
import com.rest_api.fs14backend.utils.JwtUtils;
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
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> findAllByUserId(UUID userId, String authorization) {
        String token = authorization.substring(7);
        String userIdFromToken = jwtUtils.extractUserId(token);
        if (userIdFromToken.equals(userId.toString())) {
            return transactionRepository.findAllByUserId(userId);
        }
        return null;
    }

    @Override
    public List<Transaction> findAllByBookId(UUID bookId) {
        return transactionRepository.findAllByBookId(bookId);
    }
    @Override
    public Transaction borrowBook(BorrowDto borrowDto, String authorization)  {
        UUID userId = borrowDto.getUserId();
        User foundUser = userRepository.findById(userId).orElse(null);
        UUID bookId = borrowDto.getBookId();
        Book foundBook = bookRepository.findById(bookId).orElse(null);
        System.out.println("####" + " book "+foundBook +" user "+ foundUser);
        String token = authorization.substring(7);
        String userIdFromToken = jwtUtils.extractUserId(token);
        if (userIdFromToken.equals(userId.toString())) {
            if (foundBook != null) {
                if (foundBook.isAvailable()) {
                   // System.out.println("####" + " book "+foundBook +" user "+ foundUser);
                    Transaction borrow = transactionMapper.toTransaction(foundUser, foundBook,
                            new Date(), toBeReturnedDate(borrowDto.getDay().toString()), false);
                    foundBook.setAvailable(false);
                    bookRepository.save(foundBook);
                    return transactionRepository.save(borrow);
                } else {
                    throw new RuntimeException("Book is not available");
                }
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public void returnBook(UUID transactionId, String authorization) {
        Transaction foundTransaction = transactionRepository.findById(transactionId).orElse(null);
        String token = authorization.substring(7);
        String userIdFromToken = jwtUtils.extractUserId(token);
        if (foundTransaction != null) {
            Book foundBook = bookRepository.findById(foundTransaction.getBook()).orElse(null);
            User foundUser = userRepository.findById(foundTransaction.getUser()).orElse(null);
            if (foundUser != null) {
                String userId = foundUser.getId().toString();
                if (userIdFromToken.equals(userId)) {
                    foundTransaction.setReturned(true);
                    foundTransaction.setReturnDate(new Date());
                    System.out.println("returned transaction"+ foundTransaction);
                    transactionRepository.save(foundTransaction);
                    if (foundBook != null) {
                        foundBook.setAvailable(true);
                        bookRepository.save(foundBook);
                        System.out.println("book available"+ foundBook);
                    }
                } else {
                    throw new RuntimeException("Not allowed");
                }
            }
        }
    }

    @Override
    public void deleteOne(UUID transactionId) {
        Transaction foundTransaction =  transactionRepository.findById(transactionId).orElse(null);
        if(foundTransaction != null){
            transactionRepository.deleteById(transactionId);
        } else {
            throw new TransactionNotFoundException();
        }
    }

    private Date convertLocalDateTimeToDateUsingTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    private Date toBeReturnedDate(String days) {
        Date today = new Date();
        LocalDateTime ldt = LocalDateTime.now();
        switch (days) {
            case "TEN" -> {
                return today = convertLocalDateTimeToDateUsingTimestamp(ldt.plusDays(10));
            }
            case "TWENTY" -> {
                return today = convertLocalDateTimeToDateUsingTimestamp(ldt.plusDays(20));
            }
            case "THIRTY" -> {
                return today = convertLocalDateTimeToDateUsingTimestamp(ldt.plusDays(30));
            }
        }
        return null;
    }
}
