package be;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private int id;
    private String name;
    private List<Movie> movies = new ArrayList<>();

    /**
     * Constructor with id, name, and movies
     * @param id
     * @param name
     * @param movies
     */
    public Category(int id, String name, int movies) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor with id and name
     * @param id
     * @param name
     */
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the number moviesCount
     * @return
     */
    public int getMovieCount() {
        return movies.size();
    }

    /**
     * Gets the value of id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of id
     * @return
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets value of name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of name
     * @return
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets value of movies
     * @return
     */
    public List<Movie> getMovies() { return movies; }

    /**
     * Sets value of movies
     * @return
     */
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}