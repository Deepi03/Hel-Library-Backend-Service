package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.exceptions.book.BookCannotBeDeletedException;
import com.rest_api.fs14backend.exceptions.book.BookNotFoundException;
import com.rest_api.fs14backend.transaction.Transaction;
import com.rest_api.fs14backend.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.author.AuthorService;
import com.rest_api.fs14backend.genre.Genre;
import com.rest_api.fs14backend.genre.GenreService;

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

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Book> findAll() {
        List<Book> books =  bookRepository.findAll();
       if(books.size() <= 0){
           throw new BookNotFoundException();
       } else {
           return books;
       }
    }

    @Override
    public Book findOneById(UUID id) {
        Book foundBook = bookRepository.findById(id).orElse(null);
        if(foundBook == null){
            throw new BookNotFoundException();
        } else {
            return foundBook;
        }
    }

    @Override
    public List<Book> findAllByAuthorId(UUID authorId) {
        List<Book> books =  bookRepository.findAllByAuthorId(authorId);
        if(books.size() <= 0){
            throw new BookNotFoundException();
        }
        return books;
    }

    @Override
    public List<Book> findAllByGenreId(UUID genreId) {
        List<Book> books =  bookRepository.findAllByGenreId(genreId);
        if(books.size() <= 0){
            throw new BookNotFoundException();
        }
        return books;
    }

    @Override
    public Book createOne(BookDto bookDto) {
        UUID authorId = bookDto.getAuthor();
        Author foundAuthor = authorService.findOneById(authorId);
        UUID gereId = bookDto.getGenre();
        Genre foundCategory = categoryService.findOneById(gereId);
        Book newBook = bookMapper.toBook(bookDto, foundCategory, foundAuthor);
        return bookRepository.save(newBook);
    }

    @Override
    public Book updateOneById(UUID id, BookDto bookDto) {
        Book foundBook = bookRepository.findById(id).orElse(null);
        UUID authorId = bookDto.getAuthor();
        Author foundAuthor = authorService.findOneById(authorId);
        UUID genreId = bookDto.getGenre();
        Genre foundGenre = categoryService.findOneById(genreId);

        if (foundBook != null) {
            foundBook.setIsbn(bookDto.getIsbn());
            foundBook.setTitle(bookDto.getTitle());
            foundBook.setAuthor(foundAuthor);
            foundBook.setGenre(foundGenre);
            foundBook.setPublishedDate(bookDto.getPublishedDate());
            foundBook.setPublisher(bookDto.getPublisher());
            foundBook.setCover(bookDto.getCover());
            foundBook.setDescription(bookDto.getDescription());
            foundBook.setAvailable(bookDto.isAvailable());
            return bookRepository.save(foundBook);
        }
        return null;
    }

    @Override
    public void deleteOneById(UUID bookId) {
        Book foundBook = bookRepository.findById(bookId).orElse(null);
        if(foundBook != null){
            List<Transaction> foundTransactions = transactionRepository.findAllByBookId(foundBook.getId());
            if(foundTransactions.size() > 0){
                throw new BookCannotBeDeletedException();
            }
            bookRepository.deleteById(bookId);
        } else {
            throw new BookNotFoundException();
        }
    }
}

