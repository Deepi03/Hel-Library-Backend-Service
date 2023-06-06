package com.rest_api.fs14backend.transaction;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import java.util.UUID;

import com.rest_api.fs14backend.exceptions.Transaction.TransactionBadInputRequestException;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;


    /**
     * This allows user to get his/her transactions provide user id
     * @param userId User id from path
     * @param authorization bearer token from header
     * @return list of transactions
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable UUID userId, @RequestHeader String authorization) {
        try {
            List<Transaction> foundTransactions = transactionService.findAllByUserId(userId, authorization);
            return foundTransactions.size() > 0 ? new ResponseEntity<>(foundTransactions, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (HttpClientErrorException.BadRequest e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * This method allows user to borrow a book by creating a transaction
     * @param borrowDto Borrow dto from request body
     * @param authorization Bearer token from header
     * @return created transaction
     */
    @PostMapping("/borrow")
    public ResponseEntity<Transaction> createTransaction
    (@RequestBody @Valid BorrowDto borrowDto, @RequestHeader String authorization) {
        try{
            Transaction createdTransaction  = transactionService.borrowBook(borrowDto, authorization);
            return new ResponseEntity<>(createdTransaction,HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            throw new TransactionBadInputRequestException();
        }
    }

    /**
     * This method allows user to return book by updating
     * @param transactionId
     * @param authorization
     * @return response string
     */
    @GetMapping("/return/{transactionId}")
    public ResponseEntity<String> updateTransaction(@PathVariable UUID transactionId, @RequestHeader String authorization) {
            transactionService.returnBook(transactionId, authorization);
            return new ResponseEntity<>("Book returned", HttpStatus.OK);}
}
