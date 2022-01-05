package gui.model;

import be.Movie;
import bll.MovieManager;

import java.sql.SQLException;
import java.util.List;

public class MovieModel {

    private MovieManager movieManager;

    private MovieModel() throws SQLException {
        movieManager = new MovieManager();
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
    //public Movie createMovie(String name, String rating, String fileLink){
        //return movieManager.createMovie(name, rating, fileLink);
    //}

    /**
     * Deletes a movie by taking the id and using the method from MovieDAO.
     * @param id
     * @throws Exception
     */
    public void deleteMovie(int id) throws Exception {
        movieManager.deleteMovie(id);
    }

    /**
     * Searching through a list of songs using the method from SongsDAO.
     * @param query
     * @return
     * @throws Exception
     */
    public List<Movie> searchMovie(String query) throws Exception {
        return movieManager.searchMovie(query);
    }

}
