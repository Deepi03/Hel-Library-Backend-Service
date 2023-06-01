package com.rest_api.fs14backend.transaction;


import com.rest_api.fs14backend.exceptions.Genre.GenreBadInputRequestException;
import com.rest_api.fs14backend.exceptions.Transaction.TransactionBadInputRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;


    /**
     *
     * @param userId
     * @param authorization
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
     * @param borrowDto
     * @param authorization
     * @return created transaction
     */
    @PostMapping("/borrow")
    public ResponseEntity<Transaction> createTransaction
    (@RequestBody BorrowDto borrowDto, @RequestHeader String authorization) {
        try{
            Transaction createdTransaction  = transactionService.borrowBook(borrowDto, authorization);
            return new ResponseEntity<>(createdTransaction,HttpStatus.CREATED);
        }  catch (Exception e){
            throw new TransactionBadInputRequestException();
        }
    }

    /**
     *
     * @param transactionId
     * @param authorization
     * @return response string
     */
    @GetMapping("/return/{transactionId}")
    public ResponseEntity<String> updateTransaction(@PathVariable UUID transactionId, @RequestHeader String authorization) {
        try {
            transactionService.returnBook(transactionId, authorization);
            return new ResponseEntity<>("Book returned", HttpStatus.OK);}
        catch (Exception e){
                throw new TransactionBadInputRequestException();
            }
    }

}
