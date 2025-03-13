package dev.nlsa.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        // Validate the presence of required keys
        if (!payload.containsKey("reviewBody") || !payload.containsKey("imdbId")) {
            return ResponseEntity.badRequest().body(null);
        }

        // Proceed with creating the review if validation passes
        Review createdReview = reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId"));
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }


}
