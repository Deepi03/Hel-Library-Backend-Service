package com.rest_api.fs14backend.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/authors/")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    /**
     *
     * @return list of all authors
     */
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

    /**
     *
     * @param authorId
     * @return author which matches given id
     */
    @GetMapping("/{authorId}")
    public ResponseEntity<Author> findAuthorById(@PathVariable UUID authorId) {
        try{
            Author foundAuthor = authorService.findOneById(authorId);
            return foundAuthor != null ? new ResponseEntity<>(foundAuthor,HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
