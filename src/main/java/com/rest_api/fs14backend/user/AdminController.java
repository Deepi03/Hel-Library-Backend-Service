package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.author.AuthorService;
import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookDto;
import com.rest_api.fs14backend.book.BookService;
import com.rest_api.fs14backend.genre.Genre;
import com.rest_api.fs14backend.genre.GenreService;
import com.rest_api.fs14backend.transaction.Transaction;
import com.rest_api.fs14backend.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/addAuthor")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        try{
            Author createdAuthor = authorService.createOne(author);
            return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateAuthor/{authorId}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable UUID authorId,
                                                   @RequestBody Author author) {
        try{
            Author updatedAuthor = authorService.updateOne(authorId,author);
            return updatedAuthor != null ? new ResponseEntity<>(updatedAuthor,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteAuthor/{authorId}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable UUID authorId) {
        try {
            authorService.deleteOne(authorId);
            return new ResponseEntity<>( HttpStatus.OK);
        }  catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /** ==== Book === **/

    @PostMapping("/addBook")
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        try{
            Book createdBook = bookService.createOne(bookDto);
            return new ResponseEntity<>(createdBook,HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateBook/{bookId}")
    public  ResponseEntity<Book> updateBookById(@PathVariable UUID bookId,@RequestBody BookDto bookDto){
        try{
            Book updatedBook = bookService.updateOneById(bookId,bookDto);
            return updatedBook != null ? new ResponseEntity<>(updatedBook,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<String> deleteBookId(@PathVariable UUID bookId) {
        try{
            bookService.deleteOneById(bookId);
            return new ResponseEntity<>("Book deleted",HttpStatus.OK);
        }  catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /** ==== Genre === **/

    @PostMapping("/addGenre")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        try{
            Genre createdGenre = genreService.createOne(genre);
            return new ResponseEntity<>(createdGenre,HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateGenre/{genreId}")
    public ResponseEntity<Genre> updateGenreById(@PathVariable UUID genreId,@RequestBody Genre genre) {
        try{
            Genre updatedGenre = genreService.updateOne(genreId,genre);
            return updatedGenre != null ? new ResponseEntity<>(updatedGenre,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteGenre/{genreId}")
    public ResponseEntity<String> deleteGenreById(@PathVariable UUID genreId) {
        try{
            genreService.deleteOne(genreId);
            return new ResponseEntity<>("Genre deleted",HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    @GetMapping("/allTransactions")
    public ResponseEntity<List<Transaction>> getAllTransaction(){
        try {
            List<Transaction> transactions =  transactionService.findAll();
            return transactions.size() > 0 ? new ResponseEntity<>(transactions, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}