package gui.model;

import be.Movie;
import bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class MovieModel {

    private ObservableList<Movie> moviesToBeViewed;
    private MovieManager movieManager;

    public MovieModel() throws SQLException {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getMovie());
    }

    /**
     * Edits a movie by selecting a movie and using the method from movieManager.
     * @param movie
     * @throws Exception
     */
    public void editMovie(Movie movie) throws Exception {
        movieManager.editMovie(movie);
    }

    /**
     * Gets the list of songs
     * @return List of movies
     * @throws Exception
     */
    public List<Movie> getMovies() {
        List<Movie> allMovies = movieManager.getMovie();
        return allMovies;
    }

    /**
     * Creates a movie with a given title, rating, fileLink and lastView using the method from MovieDAO.
     * @param name
     * @param rating
     * @param fileLink
     * @return movie
     */
    public Movie createMovie(String name, String rating, String fileLink, int duration){
        return movieManager.createMovie(name, rating, fileLink, duration);
    }

    /**
     * Deletes a movie by taking the id and using the method from MovieDAO.
     * @param id
     * @throws Exception
     */
    public void deleteMovie(int id) throws Exception {
        movieManager.deleteMovie(id);
    }

    /**
     *Search method for the model, searches for movies by text.
     * @param query
     * @return searchResults
     * @throws Exception
     */
    public List<Movie> searchMovie(String query) throws Exception {
        List<Movie> searchResults = null;

        searchResults = movieManager.searchMovie(query);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(searchResults);

        return searchResults;
    }

}
