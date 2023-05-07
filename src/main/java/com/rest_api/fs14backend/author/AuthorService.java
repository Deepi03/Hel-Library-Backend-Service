package com.rest_api.fs14backend.author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
   public  List<Author> findAll();
   public Author createOne(Author author);
    Author findOneById(UUID authorId);
    Author updateOne(UUID authorIdd,Author author);
    void deleteOne(UUID authorId);
}
