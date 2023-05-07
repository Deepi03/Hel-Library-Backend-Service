package com.rest_api.fs14backend.author;

import com.rest_api.fs14backend.genre.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @GetMapping()
    public ResponseEntity<List<Author>> getAllAuthors() {
        try {
            List<Author> authors =  authorService.findAll();
            return authors.size() > 0 ? new ResponseEntity<>(authors,HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping()
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        try{
            Author createdAuthor = authorService.createOne(author);
            return new ResponseEntity<>(createdAuthor,HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> findAuthorId(@PathVariable UUID authorId) {
        try{
            Author foundAuthor = authorService.findOneById(authorId);
            return foundAuthor != null ? new ResponseEntity<>(foundAuthor,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{authorId}")
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
   @DeleteMapping("/{authorId}")
   public ResponseEntity<String> deleteGenreById(@PathVariable UUID authorId) {
       try {
           authorService.deleteOne(authorId);
           return new ResponseEntity<>("Author deleted", HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
}
