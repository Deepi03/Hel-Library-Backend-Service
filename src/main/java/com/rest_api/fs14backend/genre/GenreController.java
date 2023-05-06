package com.rest_api.fs14backend.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;
    @GetMapping()
    public List<Genre> findAll(){
        return genreService.findAll();
    }
    @PostMapping()
    public Genre addOne(@RequestBody Genre genre) {
        return genreService.createOne(genre);
    }
    @GetMapping("/{id}")
    public Genre findById(@PathVariable UUID id) {
        return genreService.findOneById(id);
    }
    @PutMapping("/{id}")
    public Genre updateOne(@PathVariable UUID id,@RequestBody Genre genre) {
        return genreService.updateOne(id,genre);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable UUID id) {
        genreService.deleteOne(id);
    }

}
