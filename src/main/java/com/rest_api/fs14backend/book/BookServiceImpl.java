package com.rest_api.fs14backend.book;


import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.author.AuthorService;
import com.rest_api.fs14backend.genre.Genre;
import com.rest_api.fs14backend.genre.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    AuthorService authorService;

    @Autowired
    GenreService categoryService;

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Override
    public Book findOneById (UUID id){
        System.out.println("##### " + bookRepository.findById(id));
        return bookRepository.findById(id).orElse(null);
    }
    @Override
    public  List<Book> findAllByAuthorId(UUID authorId){
        return bookRepository.findAllByAuthorId(authorId);
    }
    @Override
    public  List<Book> findAllByGenreId(UUID genreId){
        return bookRepository.findAllByGenreId(genreId);
    }
    @Override
    public Book createOne(BookDto bookDto) {
        UUID authorId = bookDto.getAuthorId();
        Author foundAuthor = authorService.findOneById(authorId);
        UUID gereId = bookDto.getGenreId();
        Genre foundCategory = categoryService.findOneById(gereId);
        Book newBook  =  bookMapper.toBook(bookDto,foundCategory,foundAuthor);
        return bookRepository.save(newBook);
    }

    @Override
    public Book updateOneById(UUID id,BookDto bookDto){
       Book foundBook =  bookRepository.findById(id).orElse(null);
        UUID authorId = bookDto.getAuthorId();
        Author foundAuthor = authorService.findOneById(authorId);
        System.out.println("### author " +  foundAuthor);
        UUID genreId = bookDto.getGenreId();
        Genre foundGenre = categoryService.findOneById(genreId);

       if(foundBook != null){ foundBook.setIsbn(bookDto.getIsbn());
           foundBook.setTitle(bookDto.getTitle());
           foundBook.setAuthor(foundAuthor);
           foundBook.setGenre(foundGenre);
           foundBook.setPublishedDate(bookDto.getPublishedDate());
           foundBook.setPublisher(bookDto.getPublisher());
           foundBook.setCover(bookDto.getCover());
           foundBook.setDescription(bookDto.getDescription());
           return bookRepository.save(foundBook);
       }
      return null;
    }

    @Override
    public void deleteOneById(UUID id){
        bookRepository.deleteById(id);
    }


}
