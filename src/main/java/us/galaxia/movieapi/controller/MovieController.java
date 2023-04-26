package us.galaxia.movieapi.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.galaxia.movieapi.models.MovieModel;
import us.galaxia.movieapi.service.MovieService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<MovieModel>> getMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<MovieModel>> getMovieById(@PathVariable ObjectId id) {

        return new ResponseEntity<>(movieService.findMovieById(id), HttpStatus.OK);
    }

    @GetMapping("/imdb/{imdbId}")
    public ResponseEntity<Optional<MovieModel>> getMovieByImdbId(@PathVariable String imdbId) {
        return new ResponseEntity<>(movieService.findMovieByImdbId(imdbId), HttpStatus.OK);
    }

    @PostMapping("/title")
    public ResponseEntity<MovieModel> updateMovieTitleByImdbId(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<>(movieService.updateMovieTitleByImdbId(payload.get("oldTitle"), payload.get("newTitle")), HttpStatus.CREATED);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<Optional<List<MovieModel>>> getMoviesByGenre(@PathVariable String genre) {
        return new ResponseEntity<>(movieService.findMoviesByGenre(genre), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<MovieModel> createMovie(@RequestBody MovieModel movieModel) {
        return new ResponseEntity<>(movieService.createMovie(movieModel), HttpStatus.CREATED);
    }
}
