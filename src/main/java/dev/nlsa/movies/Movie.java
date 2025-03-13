package dev.nlsa.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "movies")
//lombok dependency reduces boilerplate code where backend logic will ensure different behaviour ensuring accuracy which cannot be achieved with code reusability
//takes care of different getters setters and constructors.
@Data
//ensures that all private fields can be taken as an argument
@AllArgsConstructor
//no parameters
@NoArgsConstructor
public class Movie {

//this will let application know that objectId type id will be unique id for this app
//    api contains all these fields as keys
    @Id
    private ObjectId id;

    private String imdbId;

    private String title;
    private String releaseDate;
    private String trailerLink;

    private String poster;
    private List<String> genres;
    private List<String> backdrops;

//    embedded relationship = review public class from there
// all the reviews that are going to be added will be saved in this movies collection

    @DocumentReference
    private List<Review> reviewIds;


}
