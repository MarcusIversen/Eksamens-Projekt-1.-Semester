package be;

public class Movie {

    private int id;
    private String name;
    private String rating;
    private String fileLink;
    private String lastView;
    private int duration;

    /**
     * Constructor with id, name and rating
     * @param id
     * @param name
     * @param rating
     */
    public Movie(int id, String name, String rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    /**
     * Constructor with id, name, rating and fileLink
     * @param id
     * @param name
     * @param rating
     * @param fileLink
     */
    public Movie(int id, String name, String rating, String fileLink) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
    }

    /**
     * Constructor with id, name, rating, fileLink and duration
     * @param id
     * @param name
     * @param rating
     * @param fileLink
     * @param duration
     */
    public Movie(int id, String name, String rating, String fileLink, int duration) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.duration = duration;
    }

    /**
     * Constructor with id, name, rating, fileLink and lastView
     * @param id
     * @param name
     * @param rating
     * @param fileLink
     * @param lastView
     */
    public Movie(int id, String name, String rating, String fileLink, String lastView) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.lastView = lastView;
    }

    /**
     * Constructor with id, name, rating, fileLink, lastView and duration
     * @param id
     * @param name
     * @param rating
     * @param fileLink
     * @param lastView
     * @param duration
     */
    public Movie(int id, String name, String rating, String fileLink, String lastView, int duration) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.lastView = lastView;
        this.duration = duration;
    }

    /**
     * Gets value of id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets value of id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets value of name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets value of rating
     * @return rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * Sets value of rating
     * @param rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * Gets the value of duration
     * @return
     */
    public String getDuration() {
        int minutes = (duration)/60;
        int seconds = (duration) %60;
        if (10 > seconds){
            return minutes + ":0" + seconds;
        } else {
            return minutes + ":" + seconds;
        }}

    /**
     * Sets the value of duration
     * @param duration
     */
    public void setDuration(int duration){
        this.duration = duration;
    }

    /**
     * Gets the value of fileLink
     * @return fileLink
     */
    public String getFileLink() {
        return fileLink;
    }

    /**
     * Sets the value of fileLink
     * @param fileLink
     */
    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    /**
     * Gets the value of lastView
     * @return
     */
    public String getLastView() {
        return lastView;
    }

    /**
     * Sets the value of lastView
     * @param lastView
     */
    public void setLastView(String lastView) {
        this.lastView = lastView;
    }
}