package com.rest_api.fs14backend.author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
   public  List<Author> getAllAuthors();
   public UUID createAuthor(Author author);
    Author getUserById(UUID authorId);
    Author updateAuthor(Author author);
    void deleteAuthor(UUID authorId);
}
