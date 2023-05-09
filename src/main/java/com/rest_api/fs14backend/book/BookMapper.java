package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.genre.Genre;
import org.springframework.stereotype.Component;
@Component
public class BookMapper {
    public Book toBook(BookDto bookDto, Genre genre, Author author){
        return new Book(bookDto.getTitle(), bookDto.getIsbn(),author, genre, bookDto.getPublishedDate(),
                bookDto.getPublisher(), bookDto.getCover(),bookDto.getDescription(),bookDto.isAvailable());
    }
}
