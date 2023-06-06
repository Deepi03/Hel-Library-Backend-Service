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
import jakarta.validation.Valid;
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

    /* ==== Author === **/
    /**
     *  This method allows to create author
     *
     * @param author  Author object from request body
     *
     * @return created author
     */
    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        try{
            Author createdAuthor = authorService.createOne(author);
            return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            throw new AuthorBadInputRequestException();
        }
    }

    /**
     *
     * This method is to  update an existing author provided authorId
     *
     * @param authorId : Author id from path
     * @param author : Author object with updated properties from request body
     *
     * @return update author
     */

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable UUID authorId,
                                                   @RequestBody @Valid Author author) {
        try{
            Author updatedAuthor = authorService.updateOne(authorId,author);
            return new ResponseEntity<>(updatedAuthor,HttpStatus.OK);
        } catch (DataIntegrityViolationException e){
            throw new AuthorBadInputRequestException();
        }
    }

    /**
     * This method allows to delete  existing author  provided authorId when the author is not mapped in book
     *
     * @param authorId  Author id from path
     * @return response string
     */
    @DeleteMapping("/authors/{authorId}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable UUID authorId) {
            authorService.deleteOne(authorId);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    /* ==== Book === **/

    /**
     * This method allows to create a book
     * @param bookDto BookDto object from request body
     * @return created book
     */
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        try{
            Book createdBook = bookService.createOne(bookDto);
            return new ResponseEntity<>(createdBook,HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new BookBadInputRequestException();
        }
    }

    /**
     * This method allows to update an existing book provided book id
     * @param bookId book id from path
     * @param bookDto Book dto object with updated properties from request body
     * @return updated book
     */

    @PutMapping("/books/{bookId}")
    public  ResponseEntity<Book> updateBookById(@PathVariable UUID bookId,@RequestBody BookDto bookDto){
        try{
            Book updatedBook = bookService.updateOneById(bookId,bookDto);
            return new ResponseEntity<>(updatedBook,HttpStatus.OK);
        } catch (DataIntegrityViolationException e){
            throw new BookBadInputRequestException();
        }
    }

    /**
     *
     *  This method allows to delete book provided book id when the book is not mapped in transaction
     * @param bookId book id from path
     * @return response string
     */

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<String> deleteBookId(@PathVariable UUID bookId) {
            bookService.deleteOneById(bookId);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    /** ==== Genre === **/

    /**
     * This method allows to create a genre
     *
     * @param genre Genre Object from request body
     *
     * @return created genre
     */
    @PostMapping("/genres")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        try{
            Genre createdGenre = genreService.createOne(genre);
            return new ResponseEntity<>(createdGenre,HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            throw new GenreBadInputRequestException();
        }
    }

    /**
     *
     * This method is allows to update an existing genre provide genreId
     *
     * @param genreId Genre id from path
     * @param genre  Genre object with updated data from request body
     * @return updated genre
     */

    @PutMapping("/genres/{genreId}")
    public ResponseEntity<Genre> updateGenreById(@PathVariable UUID genreId,@RequestBody Genre genre) {
        try{
            Genre updatedGenre = genreService.updateOne(genreId,genre);
            return  new ResponseEntity<>(updatedGenre,HttpStatus.OK);
        }
        catch (DataIntegrityViolationException e){
            throw new GenreBadInputRequestException();
        }
    }

    /**
     *This method allows to delete genre provide genreId  when the genre is not mapped in book
     *
     * @param genreId Genre id from path
     *
     * @return response string
     */

    @DeleteMapping("/genres/{genreId}")
    public ResponseEntity<String> deleteGenreById(@PathVariable UUID genreId) {
            genreService.deleteOne(genreId);
            return new ResponseEntity<>("Genre deleted",HttpStatus.OK);
    }



    /** ==== Users === **/

    /**
     * This method allows to get all users
     * @return list of all users
     */
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

    /**
     * This method allows to get all transactions
     * @return list of all transactions
     */
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

    /**
     *
     * @param transactionId
     * @return response string
     */

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
