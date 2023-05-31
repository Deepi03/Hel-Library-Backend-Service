package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.author.AuthorService;
import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookDto;
import com.rest_api.fs14backend.book.BookService;
import com.rest_api.fs14backend.exceptions.Genre.GenreBadInputRequestException;
import com.rest_api.fs14backend.exceptions.Genre.GenreNotFoundException;
import com.rest_api.fs14backend.exceptions.Transaction.TransactionBadInputRequestException;
import com.rest_api.fs14backend.exceptions.author.AuthorBadInputRequestException;
import com.rest_api.fs14backend.exceptions.author.AuthorNotFoundException;
import com.rest_api.fs14backend.exceptions.book.BookBadInputRequestException;
import com.rest_api.fs14backend.exceptions.book.BookNotFoundException;
import com.rest_api.fs14backend.genre.Genre;
import com.rest_api.fs14backend.genre.GenreService;
import com.rest_api.fs14backend.transaction.Transaction;
import com.rest_api.fs14backend.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    AuthorService authorService;
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    GenreService genreService;

    @Autowired
    TransactionService transactionService;

    /** ==== Author === **/
    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        try{
            Author createdAuthor = authorService.createOne(author);
            return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            throw new AuthorBadInputRequestException();
        }
    }

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable UUID authorId,
                                                   @RequestBody Author author) {
        try{
            Author updatedAuthor = authorService.updateOne(authorId,author);
            if(updatedAuthor == null )
            {
                throw new AuthorNotFoundException();
            }
            return new ResponseEntity<>(updatedAuthor,HttpStatus.OK);
        } catch (DataIntegrityViolationException e){
            throw new AuthorBadInputRequestException();
        }
    }
    @DeleteMapping("/authors/{authorId}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable UUID authorId) {
        try {
            authorService.deleteOne(authorId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new AuthorBadInputRequestException();
        }
    }

    /** ==== Book === **/

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        try{
            Book createdBook = bookService.createOne(bookDto);
            return new ResponseEntity<>(createdBook,HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            System.out.println("e ####"+e);
            throw new BookBadInputRequestException();
        }catch (Exception e){
            System.out.println("e ####"+e);
            return null;
        }

    }

    @PutMapping("/books/{bookId}")
    public  ResponseEntity<Book> updateBookById(@PathVariable UUID bookId,@RequestBody BookDto bookDto){
        try{
            Book updatedBook = bookService.updateOneById(bookId,bookDto);
            if(updatedBook == null )
            {
                throw new BookNotFoundException();
            }
            return new ResponseEntity<>(updatedBook,HttpStatus.OK);
        } catch (DataIntegrityViolationException e){
            throw new BookBadInputRequestException();
        }
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<String> deleteBookId(@PathVariable UUID bookId) {
        try {
            bookService.deleteOneById(bookId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new BookBadInputRequestException();
        }
    }

    /** ==== Genre === **/

    @PostMapping("/genres")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        try{
            Genre createdGenre = genreService.createOne(genre);
            return new ResponseEntity<>(createdGenre,HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            throw new GenreBadInputRequestException();
        }
    }

    @PutMapping("/genres/{genreId}")
    public ResponseEntity<Genre> updateGenreById(@PathVariable UUID genreId,@RequestBody Genre genre) {
        try{
            Genre updatedGenre = genreService.updateOne(genreId,genre);
            if(updatedGenre == null )
            {
                throw new GenreNotFoundException();
            }
            return  new ResponseEntity<>(updatedGenre,HttpStatus.OK);
        }
        catch (DataIntegrityViolationException e){
            throw new GenreBadInputRequestException();
        }
    }

    @DeleteMapping("/genres/{genreId}")
    public ResponseEntity<String> deleteGenreById(@PathVariable UUID genreId) {
        try{
            genreService.deleteOne(genreId);
            return new ResponseEntity<>("Genre deleted",HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new GenreBadInputRequestException();
        }

    }

    /** ==== Users === **/
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users =  userService.findAll();
            return users.size() > 0 ? new ResponseEntity<>(users, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /** ==== Transactions === **/
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransaction(){
        try {
            List<Transaction> transactions =  transactionService.findAll();
            return transactions.size() > 0 ? new ResponseEntity<>(transactions, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/transactions/{transactionId}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable UUID transactionId) {
        try {
            transactionService.deleteOne(transactionId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            throw new TransactionBadInputRequestException();
        }
    }
}
