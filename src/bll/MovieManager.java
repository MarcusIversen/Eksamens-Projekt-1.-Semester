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
     * Constructor
     */
    public MovieManager(){
        movieSearcher = new MovieSearcher();
        movieDAO = new MovieDAO();
    }

    /**
     * Gets list of movies using getMovies method from movieDAO
     * @return a list of movie or an empty list of movies
     */
    public List<Movie> getMovie() {
        List<Movie> allMovie = movieDAO.getMovies();
        return allMovie;
    }

    /**
     * Creates a movie using createMovie method from movieDAO
     * @param name
     * @param rating
     * @param fileLink
     * @param duration
     * @return a movie with name, rating, fileLink and duration
     */
    public Movie createMovie(String name, String rating, String fileLink, int duration) {
        return movieDAO.createMovie(name, rating, fileLink, duration);
    }

    /**
     * Deletes a movie by taking the ID and using the method from MovieDAO
     * @param id
     */
    public void deleteMovie(int id){
        movieDAO.deleteMovie(id);
    }

    /**
     * Edits a movie by selecting a movie and using the method from MovieDAO.
     * @param movie
     */
    public void editMovie(Movie movie){
        movieDAO.editMovie(movie);
    }

    /**
     * Searching through a list of movies using the method from MovieDAO.
     * @param query
     * @return a list of searched movies or an empty list of searched movies
     */
    public List<Movie> searchMovie(String query){
        List<Movie> allMovies = getMovie();
        List<Movie> searchResult = movieSearcher.search(allMovies, query);
        return searchResult;
    }


}
