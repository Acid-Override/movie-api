package us.galaxia.movieapi.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import us.galaxia.movieapi.models.ReviewModel;

@Repository
public interface ReviewRepository extends MongoRepository<ReviewModel, ObjectId> {

}
