package com.rest_api.fs14backend.book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    public List<Book> findAll();
    public Book findOneById(UUID id);
    public List<Book> findAllByAuthorId(UUID authorId);
    public List<Book> findAllByGenreId(UUID genreId);
    public Book createOne(BookDto bookDto);
    public Book updateOneById(UUID id,BookDto bookDto);
    public void deleteOneById(UUID id);


}
