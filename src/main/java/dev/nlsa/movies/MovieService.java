package dev.nlsa.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//service annotation will help the application to process the request received. To process the request,

// the connection needs to be established that's why we are using autowired to initialise the repo class and then
//service annotation.
@Service
public class MovieService {

//    autowired will inform the application that this movieRepository will get automatically instantiated in this class
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> allMovies() {
//        findAll() inside the repository class , inbuilt
        return movieRepository.findAll();
    }

//    optional movie return type will handle null return value cases
    public Optional<Movie> singleMovie(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }
}
