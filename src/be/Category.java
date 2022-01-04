package be;

public class Category {

    private int id;
    private String name;
    private String numberOfMovies;

    public Category(int id, String name, String numberOfMovies){
        this.id = id;
        this.name = name;
        this.numberOfMovies = numberOfMovies;
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

    public String getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(String numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }
}