package dal.Interfaces;

import be.Movie;
import java.util.List;

public interface MovieDAOInterface {

    /**
     * Making a movie list, connecting to the database and adding the results to our ArrayList.
     *
     * @return a list of movie or an empty list of movie
     */
    List<Movie> getMovies();

    /**
     * Creates a movie, by inserting a giving name, rating, fileLink and duration
     *
     * @param name
     * @param rating
     * @param fileLink
     * @param duration
     * @return a movie with a giving name, rating, fileLink and duration
     */
    Movie createMovie(String name, String rating, String fileLink, int duration);

    /**
     * Deletes a movie, by taking the movieId
     *
     * @param id
     */
    void deleteMovie(int id);

    /**
     * Changes the name of a movie if a match is found
     *
     * @param movie
     */
    void editMovie(Movie movie);
}
