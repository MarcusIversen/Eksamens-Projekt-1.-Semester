package be;

public class Movie {

    private int id;
    private String name;
    private String rating;
    private String fileLink;
    private String lastView;
    private int duration;

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

    public Movie(int id, String name, String rating, String fileLink, int duration) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.duration = duration;
    }

    public Movie(int id, String name, String rating, String fileLink, String lastView, int duration) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.lastView = lastView;
        this.duration = duration;
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

    public void setDuration(int duration) {this.duration = duration;}

    public String getDuration() {
        int minuts = (duration)/60;
        int seconds = (duration) %60;
        if (10 > seconds){
            return minuts + ":0" + seconds;
        } else {
            return minuts + ":" + seconds;
        }}

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