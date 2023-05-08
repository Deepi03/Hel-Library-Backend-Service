package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.genre.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;
    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks(){
        try {
            List<Book> books =  bookService.findAll();
            return books.size() > 0 ? new ResponseEntity<>(books, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
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
    @GetMapping("/{bookId}/")
    public ResponseEntity<Book> findBookById(@PathVariable UUID bookId){
        try{
            Book foundBook = bookService.findOneById(bookId);
            return foundBook != null ? new ResponseEntity<>(foundBook,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/authors/{authorId}")
        public ResponseEntity<List<Book>> findBookByAuthorId(@PathVariable UUID authorId){
            try{
                List<Book> foundBooks = bookService.findAllByAuthorId(authorId);
                return foundBooks.size() > 0 ? new ResponseEntity<>(foundBooks,HttpStatus.OK)
                        : new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
            } catch(Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    @GetMapping("/genres/{genreId}")
    public ResponseEntity<List<Book>> findBooksByGenreId(@PathVariable UUID genreId){
        try{
            List<Book> foundBooks = bookService.findAllByGenreId(genreId);
            return foundBooks.size() > 0 ? new ResponseEntity<>(foundBooks,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @PutMapping("/{bookId}/")
    public  ResponseEntity<Book> updateBookById(@PathVariable UUID bookId,@RequestBody BookDto bookDto){
        try{
            Book updatedBook = bookService.updateOneById(bookId,bookDto);
            return updatedBook != null ? new ResponseEntity<>(updatedBook,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBookId(@PathVariable UUID bookId) {
        try{
            bookService.deleteOneById(bookId);
            return new ResponseEntity<>("Book deleted",HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
