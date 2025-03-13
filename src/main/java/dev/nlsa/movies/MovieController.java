package dev.nlsa.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")

@RestController
//instead of localhost8080, we will use user defined uri inside request mapping
//client making request to this server

@RequestMapping("/api/v1/movies")

//after the connection is established and request has been processed, (@Autowired done) the request fetched
//from the database will be sent to the client
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping()
//    getmapping receives the call request of the function from the front end request.
//   NOTE: if getmapping was not used, the function getAllMovies wouldn't be called whenever GET method was made.
//    movieService containing the database access and performing the get method will be utilised here
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<List<Movie>>(movieService.allMovies(),HttpStatus.OK);
    }

//    path variable extract values from the uri path, from getmapping annotation
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId) {
        return new ResponseEntity<Optional<Movie>>(movieService.singleMovie(imdbId),HttpStatus.OK);
    }

}
