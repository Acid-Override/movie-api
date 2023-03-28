package us.galaxia.movieapi.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import us.galaxia.movieapi.models.MovieModel;
import us.galaxia.movieapi.models.ReviewModel;
import us.galaxia.movieapi.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public ReviewModel createReview(String reviewBody, String imdbId) {
        ReviewModel reviewModel = reviewRepository.insert(new ReviewModel(reviewBody));

        mongoTemplate.update(MovieModel.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(reviewModel))
                .first();

        return reviewModel;
    }

    public Optional<ReviewModel> findById (ObjectId id) {
        return reviewRepository.findById(id);
    }

    public List<ReviewModel> getAllReviews() {
        return reviewRepository.findAll();
    }
}
