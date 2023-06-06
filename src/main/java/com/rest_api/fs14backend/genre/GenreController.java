package com.rest_api.fs14backend.genre;

import com.rest_api.fs14backend.author.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/genres/")
public class GenreController {
    @Autowired
    private GenreService genreService;

    /**
     *
     * @return list of genres
     */
    @GetMapping()
    public ResponseEntity<List<Genre>> getAllGenres() {
        try {
            List<Genre> genres = genreService.findAll();
            return genres.size() > 0 ? new ResponseEntity<>(genres, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param genreId
     * @return genre which matches given id
     */

    @GetMapping("/{genreId}")
    public ResponseEntity<Genre> findGenreById(@PathVariable UUID genreId) {
            Genre foundGenre = genreService.findOneById(genreId);
            return  new ResponseEntity<>(foundGenre, HttpStatus.OK);
    }
}
