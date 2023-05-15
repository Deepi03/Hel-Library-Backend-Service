package com.rest_api.fs14backend.transaction;


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



    @GetMapping("/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable UUID userId,@RequestHeader String authorization){
        try{
            List<Transaction> foundTransactions = transactionService.findAllByUserId(userId,authorization);
            return foundTransactions.size() > 0 ? new ResponseEntity<>(foundTransactions,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        }
        catch (HttpClientErrorException.BadRequest e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /** Borrow Book **/
    @PostMapping("/borrow")
    public ResponseEntity<String> createTransaction(@RequestBody BorrowDto borrowDto,@RequestHeader String authorization){
            transactionService.borrowBook(borrowDto,authorization);
            return new ResponseEntity<>("Book borrowed", HttpStatus.OK);
    }
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable UUID transactionId,@RequestHeader String authorization){
        try{
            transactionService.returnBook(transactionId,authorization);
            return new ResponseEntity<>("Book returned", HttpStatus.OK);
        }  catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
