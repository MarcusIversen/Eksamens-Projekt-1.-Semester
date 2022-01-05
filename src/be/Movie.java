package be;

public class Movie {

    private int id;
    private String name;
    private String rating;
    private String fileLink;
    private String lastView;

    public Movie(int id, String name, String rating, String fileLink, String lastView) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.lastView = lastView;
    }

    public Movie(int id, String name, String rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;

    }

    public Movie(int id, String name, String rating, String fileLink) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getLastView() {
        return lastView;
    }

    public void setLastView(String lastView) {
        this.lastView = lastView;
    }
}