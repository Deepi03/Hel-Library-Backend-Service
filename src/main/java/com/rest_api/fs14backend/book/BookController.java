package com.rest_api.fs14backend.book;

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

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookService.findAll();
            return books.size() > 0 ? new ResponseEntity<>(books, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{bookId}")
    public ResponseEntity<Book> findBookById(@PathVariable UUID bookId) {
        try {
            Book foundBook = bookService.findOneById(bookId);
            return foundBook != null ? new ResponseEntity<>(foundBook, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("authors/{authorId}")
    public ResponseEntity<List<Book>> findBookByAuthorId(@PathVariable UUID authorId) {
        try {
            List<Book> foundBooks = bookService.findAllByAuthorId(authorId);
            return foundBooks.size() > 0 ? new ResponseEntity<>(foundBooks, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("genres/{genreId}")
    public ResponseEntity<List<Book>> findBooksByGenreId(@PathVariable UUID genreId) {
        try {
            List<Book> foundBooks = bookService.findAllByGenreId(genreId);
            return foundBooks.size() > 0 ? new ResponseEntity<>(foundBooks, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
