package dev.nlsa.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

//    mongoTemplate works same as Repository but for complex queries, we use mongoTemplate which also contains dynamic updation
    @Autowired
    private MongoTemplate mongoTemplate;

//    string imdbId received from the user.

//    NOTE: WE ARE JUST UPDATING THE EMPTY reviewIds ARRAY, WHERE ARRAY WILL BE MATCHED USING IMDBID
//  COMPARING THE IMDBID IN THE DATABASE (KEY) TO THE IMDBID WE HAVE RECEIVED FROM THE USER.
//    reviewbody = content of the review
    public Review createReview(String reviewBody,String imdbId) {
//        when you insert, it returns the data you pushed inside the database also for further utilisation in the program
        Review review = reviewRepository.insert(new Review(reviewBody));

//      complex query = update the movies
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

                return review;
    }

    }

