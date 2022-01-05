package bll;

import be.Movie;
import dal.MovieDAO;

import java.sql.SQLException;
import java.util.List;

public class MovieManager {

    private MovieDAO movieDAO = new MovieDAO();

    public MovieManager() throws SQLException {

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

    public Movie createMovie(String title, String rating, String fileLink){
        return movieDAO.createMovie(title, rating, fileLink);
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
        return movieDAO.searchMovie(query);
    }


}
