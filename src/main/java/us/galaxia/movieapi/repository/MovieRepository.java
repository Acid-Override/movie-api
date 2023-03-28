package us.galaxia.movieapi.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import us.galaxia.movieapi.models.MovieModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<MovieModel, ObjectId> {
    Optional<MovieModel> findMovieModelByImdbId(String imdbId);
    Optional<List<MovieModel>> findMovieModelByGenres (String genre);
}
