package com.rest_api.fs14backend.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;
    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
    @Override
    public Genre findOneById(UUID genreId)  {
        return genreRepository.findById(genreId).orElse(null);
    }
    @Override
    public Genre createOne(Genre genre){
        return genreRepository.save(genre);
    }
    @Override
    public Genre updateOne( UUID genreId,@RequestBody Genre genre) {
        Genre foundGenre = genreRepository.findById(genreId).orElse(null);
        if(foundGenre != null){
            foundGenre.setName(genre.getName());
            foundGenre.setCoverImage(genre.getCoverImage());
            foundGenre.setDescription(genre.getDescription());
            return genreRepository.save(foundGenre);
        }
        return  null;
    }
    @Override
    public void deleteOne(UUID genreId)  {
        genreRepository.deleteById(genreId);
    }

}
