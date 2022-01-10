package be;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private int id;
    private String name;
    private List<Movie> movies = new ArrayList<>();

    /**
     *
     * @param id
     * @param name
     * @param movies
     */
    public Category(int id, String name, int movies) {
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @param id
     * @param name
     */
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getMovieCount() {
        return movies.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() { return movies; }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}