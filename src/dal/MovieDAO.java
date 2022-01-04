package dal;

import be.Movie;
import dal.db.DatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    private final DatabaseConnector databaseConnector;

    /**
     * Making a reference to the databaseConnector, so we can connect to the SQL Database.
     */
    public MovieDAO() throws SQLException {
        databaseConnector = new DatabaseConnector();
    }

    public List<Movie> getMovies(){
        ArrayList<Movie> allMovies = new ArrayList<>();

        return allMovies;
    }

    public Movie createMovie(){
        return null;
    }

    public void deleteMovie(int id){

    }

    public void editMovie(){

    }

    public static void main(String[] args) {

    }
}
