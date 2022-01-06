package bll;

import be.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher {
    /**
     * Method for searching a movie with the search button.
     * @param searchBase
     * @param query
     * @return
     */

    public List<Movie> search(List<Movie> searchBase, String query) {
        List<Movie> searchResult = new ArrayList<>();

        for (Movie movie : searchBase) {
            if (compareToMovieTitle(query, movie)) {
                searchResult.add(movie);
            }
        }

        return searchResult;
    }


    /**
     * Compares the movie by title and what it contains.
     * If you write "s", it finds a movie that contains "s"
     * @param query
     * @param movie
     * @return
     */
    private boolean compareToMovieTitle(String query, Movie movie) {
        return movie.getName().toLowerCase().contains(query.toLowerCase());
    }
}
