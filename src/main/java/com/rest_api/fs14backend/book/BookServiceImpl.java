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

    /**
     *
     * @return list all books
     */
    @Override
    public List<Book> findAll() {
        List<Book> books =  bookRepository.findAll();
       if(books.size() <= 0){
           throw new BookNotFoundException();
       } else {
           return books;
       }
    }

    /**
     *
     * @param id
     * @return book which matches given id
     */
    @Override
    public Book findOneById(UUID id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);

    }

    /**
     *
     * @param authorId
     * @return list of all books which matches given author id
     */

    @Override
    public List<Book> findAllByAuthorId(UUID authorId) {
        List<Book> books =  bookRepository.findAllByAuthorId(authorId);
        if(books.size() <= 0){
            throw new BookNotFoundException();
        }
        return books;
    }

    /**
     *
     * @param genreId
     * @return list of all books which matches given genre id
     */

    @Override
    public List<Book> findAllByGenreId(UUID genreId) {
        List<Book> books =  bookRepository.findAllByGenreId(genreId);
        if(books.size() <= 0){
            throw new BookNotFoundException();
        }
        return books;
    }

    /**
     *
     * @param bookDto
     * @return created book
     */

    @Override
    public Book createOne(BookDto bookDto) {
        UUID authorId = bookDto.getAuthor();
        Author foundAuthor = authorService.findOneById(authorId);
        UUID gereId = bookDto.getGenre();
        Genre foundCategory = categoryService.findOneById(gereId);
        Book newBook = bookMapper.toBook(bookDto, foundCategory, foundAuthor);
        return bookRepository.save(newBook);
    }

    /**
     *
     * @param id
     * @param bookDto
     * @return updated book
     */
    @Override
    public Book updateOneById(UUID id, BookDto bookDto) {
        Book foundBook = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        UUID authorId = bookDto.getAuthor();
        Author foundAuthor = authorService.findOneById(authorId);
        UUID genreId = bookDto.getGenre();
        Genre foundGenre = categoryService.findOneById(genreId);
        foundBook.setIsbn(bookDto.getIsbn());
        foundBook.setTitle(bookDto.getTitle());
        foundBook.setAuthor(foundAuthor);
        foundBook.setGenre(foundGenre);
        foundBook.setPublishedDate(bookDto.getPublishedDate());
        foundBook.setPublisher(bookDto.getPublisher());
        foundBook.setCover(bookDto.getCover());
        foundBook.setDescription(bookDto.getDescription());
        return bookRepository.save(foundBook);
    }

    /**
     *
     * @param bookId
     */

    @Override
    public void deleteOneById(UUID bookId) {
        Book foundBook = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        List<Transaction> foundTransactions = transactionRepository.findAllByBookId(foundBook.getId());
        if(foundTransactions.size() > 0){
                throw new BookCannotBeDeletedException();
            }
            bookRepository.deleteById(bookId);

    }
}

