package com.rest_api.fs14backend.genre;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookRepository;
import com.rest_api.fs14backend.exceptions.Genre.GenreCannotBeDeletedException;
import com.rest_api.fs14backend.exceptions.Genre.GenreNotFoundException;
import com.rest_api.fs14backend.exceptions.author.AuthorCannotBeDeletedException;
import com.rest_api.fs14backend.exceptions.author.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;

    /**
     *
     * @return list of genres
     */
    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    /**
     *
     * @param genreId
     * @return genre which matches the given id
     */
    @Override
    public Genre findOneById(UUID genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }

    /**
     *
     * @param genre
     * @return created genre
     */

    @Override
    public Genre createOne(Genre genre) {
        return genreRepository.save(genre);
    }

    /**
     *
     * @param genreId
     * @param genre
     * @return updated genre
     */

    @Override
    public Genre updateOne(UUID genreId, @RequestBody Genre genre) {
        Genre foundGenre = genreRepository.findById(genreId).orElse(null);
        if (foundGenre != null) {
            foundGenre.setName(genre.getName());
            foundGenre.setCoverImage(genre.getCoverImage());
            foundGenre.setDescription(genre.getDescription());
            return genreRepository.save(foundGenre);
        }
        return null;
    }

    /**
     *
     * @param genreId
     */

    @Override
    public void deleteOne(UUID genreId) {
        Genre foundGenre =  findOneById(genreId);
        if(foundGenre != null){
            List<Book> foundBooks = bookRepository.findAllByAuthorId(foundGenre.getId());
            if(foundBooks.size() > 0){
                throw new GenreCannotBeDeletedException();
            }
            genreRepository.deleteById(genreId);
        } else {
            throw new GenreNotFoundException();
        }
    }
}
