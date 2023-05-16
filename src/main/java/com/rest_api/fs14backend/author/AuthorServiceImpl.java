package com.rest_api.fs14backend.author;

import com.rest_api.fs14backend.book.BookRepository;
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

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author createOne(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author findOneById(UUID authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    @Override
    public Author updateOne(UUID authorId, Author author) {
        Author foundAuthor = authorRepository.findById(authorId).orElse(null);
        if (foundAuthor != null) {
            foundAuthor.setName(author.getName());
            foundAuthor.setInfo(author.getInfo());
            foundAuthor.setImage(author.getImage());
            return authorRepository.save(foundAuthor);
        }
        return null;
    }

    @Override
    public void deleteOne(UUID authorId) {
        Author foundAuthor = authorRepository.findById(authorId).orElse(null);
        if (bookRepository.findAllByAuthorId(foundAuthor.getId()) == null) {
            authorRepository.deleteById(authorId);
        }
        throw new RuntimeException("cannot delete book");
    }
}
