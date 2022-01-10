package bll;

import be.Movie;
import bll.helpers.MovieSearcher;
import dal.MovieDAO;

import java.sql.SQLException;
import java.util.List;

public class MovieManager {

    private MovieSearcher movieSearcher;
    private MovieDAO movieDAO;

    /**
     *
     * @throws SQLException
     */
    public MovieManager() throws SQLException {
        movieSearcher = new MovieSearcher();
        movieDAO = new MovieDAO();
    }

    /**
     * Gets the list of movies
     * @return
     * @throws Exception
     */
    public List<Movie> getMovie() {
        List<Movie> allMovie = movieDAO.getMovies();
        return allMovie;
    }

    /**
     *
     * @param name
     * @param rating
     * @param fileLink
     * @param duration
     * @return
     */
    public Movie createMovie(String name, String rating, String fileLink, int duration){
        return movieDAO.createMovie(name, rating, fileLink, duration);
    }

    /**
     * Deletes a movie by taking the ID and using the method from MovieDAO.
     * @param id
     * @throws Exception
     */
    public void deleteMovie(int id) throws Exception {
        movieDAO.deleteMovie(id);
    }

    /**
     * Edits a movie by selecting a movie and using the method from MovieDAO.
     * @param movie
     * @throws Exception
     */
    public void editMovie(Movie movie) throws Exception {
        movieDAO.editMovie(movie);
    }

    /**
     * Searching through a list of movies using the method from MovieDAO.
     * @param query
     * @return
     * @throws Exception
     */
    public List<Movie> searchMovie(String query) throws Exception {
        List<Movie> allMovies = getMovie();
        List<Movie> searchResult = movieSearcher.search(allMovies, query);
        return searchResult;
    }


}
