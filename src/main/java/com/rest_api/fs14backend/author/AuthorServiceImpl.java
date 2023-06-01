package com.rest_api.fs14backend.author;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookRepository;
import com.rest_api.fs14backend.exceptions.author.AuthorCannotBeDeletedException;
import com.rest_api.fs14backend.exceptions.author.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    /**
     *
     * @return list of all authors
     */
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    /**
     *
     * @param authorId
     * @return author which matches given id
     */

    @Override
    public Author findOneById(UUID authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }


    /**
     *
     * @param author
     * @return created author
     */

    @Override
    public Author createOne(Author author) {
        return authorRepository.save(author);
    }


    /**
     *
     * @param authorId
     * @param author
     * @return updated author
     */

    @Override
    public Author updateOne(UUID authorId, Author author) {
        Author foundAuthor = findOneById(authorId);
        if (foundAuthor != null) {
            foundAuthor.setName(author.getName());
            foundAuthor.setInfo(author.getInfo());
            foundAuthor.setImage(author.getImage());
            return authorRepository.save(foundAuthor);
        }
        return null;
    }

    /**
     *
     * @param authorId
     */

    @Override
    public void deleteOne(UUID authorId) {
        Author foundAuthor =  findOneById(authorId);
        if(foundAuthor != null){
            List<Book> foundBooks = bookRepository.findAllByAuthorId(foundAuthor.getId());
            if(foundBooks.size() > 0){
                throw new AuthorCannotBeDeletedException();
            }
            authorRepository.deleteById(authorId);
        } else {
           throw new AuthorNotFoundException();
        }
    }
}
