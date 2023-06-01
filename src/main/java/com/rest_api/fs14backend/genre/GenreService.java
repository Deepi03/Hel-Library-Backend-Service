package com.rest_api.fs14backend.genre;

import java.util.List;
import java.util.UUID;


public interface GenreService {


    List<Genre> findAll();

    Genre createOne(Genre newGenre);

    Genre findOneById(UUID genreId);

    Genre updateOne(UUID genreId, Genre genre);

    void deleteOne(UUID genreId);
}
