package com.rest_api.fs14backend.transaction;


import com.rest_api.fs14backend.book.BookService;
import com.rest_api.fs14backend.exceptions.Transaction.*;
import com.rest_api.fs14backend.exceptions.book.BookNotAvailableException;
import com.rest_api.fs14backend.exceptions.book.BookNotFoundException;
import com.rest_api.fs14backend.user.UserService;
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

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    private JwtUtils jwtUtils;


    /**
     * Get all Transactions
     * @return list of transactions
     */
    @Override
    public List<Transaction> findAll() {
            return transactionRepository.findAll();
    }

    /**
     * Get Transaction by id
     * @param userId
     * @param authorization
     * @return  list of transactions which matches the given user id
     */
    @Override
    public List<Transaction> findAllByUserId(UUID userId, String authorization) {
        String token = authorization.substring(7);
        String userIdFromToken = jwtUtils.extractUserId(token);
        if (userIdFromToken.equals(userId.toString())) {
            return transactionRepository.findAllByUserId(userId);
        }
        return null;
    }

    /**
     *  Get all transaction based on book id
     * @param bookId
     * @return list of transactions which matches given book id
     */
    @Override
    public List<Transaction> findAllByBookId(UUID bookId) {
        return transactionRepository.findAllByBookId(bookId);
    }

    /**
     * Borrow book
     * @param borrowDto
     * @param authorization
     * @return created transaction
     */
    @Override
    public Transaction borrowBook(BorrowDto borrowDto, String authorization)  {
        UUID userId = borrowDto.getUserId();
        String token = authorization.substring(7);
        String userIdFromToken = jwtUtils.extractUserId(token);
        if (userIdFromToken.equals(userId.toString())) {
            User foundUser =userService.findOneById(userId);
            UUID bookId = borrowDto.getBookId();
            Book foundBook =bookService.findOneById(bookId);
            if (foundBook != null) {
                if (foundBook.isAvailable()) {
                    Transaction borrow = transactionMapper.toTransaction(foundUser, foundBook,
                            new Date(), toBeReturnedDate(borrowDto.getDay().toString()), false);
                    foundBook.setAvailable(false);
                    bookRepository.save(foundBook);
                    return transactionRepository.save(borrow);
                } else {
                    throw new BookNotAvailableException();
                }
            } else {
                throw new BookNotFoundException();
            }
        } else {
            throw new IllegalUserAccessException();
        }

    }

    /**
     * Return book
     * @param transactionId
     * @param authorization
     */

    @Override
    public void returnBook(UUID transactionId, String authorization) {
        Transaction foundTransaction = transactionRepository.findById(transactionId).orElse(null);
        String token = authorization.substring(7);
        String userIdFromToken = jwtUtils.extractUserId(token);
        if (foundTransaction != null) {
            Book foundBook = bookService.findOneById(foundTransaction.getBook());
            User foundUser = userService.findOneById(foundTransaction.getUser());
            if (foundUser != null) {
                String userId = foundUser.getId().toString();
                if (userIdFromToken.equals(userId)) {
                if(!foundTransaction.isReturned()){
                        foundTransaction.setReturned(true);
                        foundTransaction.setReturnDate(new Date());
                        transactionRepository.save(foundTransaction);
                        if (foundBook != null) {
                            foundBook.setAvailable(true);
                            bookRepository.save(foundBook);
                        } else {
                            throw new BookNotFoundException();
                        }
                    } else {
                        throw new TransactionCannotBeReturned();
                    }
                } else  {
                    throw new IllegalUserAccessException();
                }
            }
        } else {
            throw new TransactionNotFoundException();
        }
    }

    /**
     * Delete Transaction
     * @param transactionId
     */

    @Override
    public void deleteOne(UUID transactionId) {
        Transaction foundTransaction =  transactionRepository.findById(transactionId).orElse(null);
        if(foundTransaction != null){
            if(!foundTransaction.isReturned()){
                throw new TransactionCannotBeDeletedException();
            }
            transactionRepository.deleteById(transactionId);
        } else {
            throw new TransactionNotFoundException();
        }
    }

    /**
     *
     * @param localDateTime
     * @return converted local date
     */
    private Date convertLocalDateTimeToDateUsingTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    /**
     *
     * @param days
     * @return matched date
     */

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
