package com.rest_api.fs14backend.transaction;

import com.rest_api.fs14backend.genre.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping()
        public ResponseEntity<List<Transaction>> getAllTransaction(){
        try {
            List<Transaction> transactions =  transactionService.findAll();
            return transactions.size() > 0 ? new ResponseEntity<>(transactions, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable UUID userId){
        try{

            List<Transaction> foundTransactions = transactionService.findAllByUserId(userId);
            return foundTransactions.size() > 0 ? new ResponseEntity<>(foundTransactions,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/borrow")
    public ResponseEntity<String> createTransaction(@RequestBody BorrowDto borrowDto){
            transactionService.borrowBook(borrowDto);
            return new ResponseEntity<>("Book borrowed", HttpStatus.OK);
    }
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable UUID transactionId){
        try{
            transactionService.returnBook(transactionId);
            return new ResponseEntity<>("Book returned", HttpStatus.OK);
        }  catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
