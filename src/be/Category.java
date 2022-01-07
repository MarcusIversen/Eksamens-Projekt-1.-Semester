package be;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private int id;
    private String name;
    private List<Movie> movies = new ArrayList<>();

    public Category(int id, String name, int movies){
        this.id = id;
        this.name = name;
    }

    public Category(int id, String name){
        this.id = id;
        this.name = name;
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

    public int getMovieCount() {
        return movies.size();
    }
    public List<Movie> getMovies() {return movies;}

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}