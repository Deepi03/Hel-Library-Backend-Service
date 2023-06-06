package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.exceptions.author.AuthorBadInputRequestException;
import com.rest_api.fs14backend.exceptions.author.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books/")
public class BookController {

    @Autowired
    BookService bookService;

    /**
     * This method allows to get all books
     *
     * @return list of all books
     */
    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> foundBooks = bookService.findAll();
        return  new ResponseEntity<>(foundBooks, HttpStatus.OK);
    }

    /**
     *
     *  This method allows to get book provided book id
     *
     * @param bookId book id from path
     * @return book which matches the given id
     */

    @GetMapping("{bookId}")
    public ResponseEntity<Book> findBookById(@PathVariable UUID bookId) {
            Book foundBook = bookService.findOneById(bookId);
            return new ResponseEntity<>(foundBook,HttpStatus.OK) ;
    }

    /**
     *
     * This method allows get all books of an author provided author id
     *
     * @param authorId author id from path
     * @return list of all books which matches given author id
     */

    @GetMapping("authors/{authorId}")
    public ResponseEntity<List<Book>> findBookByAuthorId(@PathVariable UUID authorId) {
            List<Book> foundBooks = bookService.findAllByAuthorId(authorId);
            return  new ResponseEntity<>(foundBooks, HttpStatus.OK);
    }

    /**
     *
     * This method allows get all books of an author provided genre id
     * @param genreId genre id from path
     *
     * @return list of all books which matches given genre id
     */

    @GetMapping("genres/{genreId}")
    public ResponseEntity<List<Book>> findBooksByGenreId(@PathVariable UUID genreId) {
            List<Book> foundBooks = bookService.findAllByGenreId(genreId);
            return  new ResponseEntity<>(foundBooks, HttpStatus.OK);
    }
}
