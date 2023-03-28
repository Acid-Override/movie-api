package us.galaxia.movieapi.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.galaxia.movieapi.repository.MovieRepository;
import us.galaxia.movieapi.models.MovieModel;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieModel> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<MovieModel> findMovieById(ObjectId id) {
        return movieRepository.findById(id);
    }

    public Optional<MovieModel> findMovieByImdbId(String imdbId) {
        return movieRepository.findMovieModelByImdbId(imdbId);
    }

    public Optional<List<MovieModel>> findMoviesByGenre(String genre) {
        return movieRepository.findMovieModelByGenres(genre);
    }
}
