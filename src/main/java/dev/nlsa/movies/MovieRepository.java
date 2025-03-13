package dev.nlsa.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//repository annotation is used to access data i.e. interaction with the database.
@Repository
//generic type
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

//    springframework is smart enough to understand the user defined method by itself
    Optional<Movie> findMovieByImdbId(String imdbId);
}
