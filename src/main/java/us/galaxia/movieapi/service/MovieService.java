package us.galaxia.movieapi.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import us.galaxia.movieapi.repository.MovieRepository;
import us.galaxia.movieapi.models.MovieModel;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

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

    public MovieModel createMovie(MovieModel movieModel) {
        return movieRepository.insert(movieModel);
    }

    public MovieModel updateMovieTitleByImdbId(String oldTitle, String newTitle) {
        Query query = new Query().addCriteria(Criteria.where("title").is(oldTitle));
        Update updateDefinition = new Update().set("title", newTitle);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(false);

        return mongoTemplate.findAndModify(query, updateDefinition, options, MovieModel.class);
    }
}
