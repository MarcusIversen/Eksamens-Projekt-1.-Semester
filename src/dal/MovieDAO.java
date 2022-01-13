package dal;

import be.ErrorHandling;
import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.Interfaces.MovieDAOInterface;
import dal.db.DatabaseConnector;
import javafx.scene.control.Alert;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements MovieDAOInterface {

    private ErrorHandling errorHandling;
    private final DatabaseConnector databaseConnector;

    /**
     * Making a reference to the databaseConnector, so we can connect to the SQL Database.
     */
    public MovieDAO(){
        databaseConnector = new DatabaseConnector();
        errorHandling = new ErrorHandling();
    }

    /**
     * Making a movie list, connecting to the database and adding the results to our ArrayList.
     * @return a list of movie or an empty list of movie
     */
    public List<Movie> getMovies() {

        ArrayList<Movie> allMovies = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {

            String sql = "SELECT * FROM Movie;";

            Statement statement = connection.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultset = statement.getResultSet();
                while (resultset.next()) {
                    int id = resultset.getInt("id");
                    String name = resultset.getString("name");
                    String rating = resultset.getString("rating");
                    String filelink = resultset.getString("filelink");
                    String lastView = resultset.getString("lastView");
                    int duration = resultset.getInt("duration");

                    Movie movie = new Movie(id, name, rating, filelink, lastView, duration);
                    allMovies.add(movie);
                }
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allMovies;
    }

    /**
     * Creates a movie, by inserting a giving name, rating, fileLink and duration
     * @param name
     * @param rating
     * @param fileLink
     * @param duration
     * @return a movie with a giving name, rating, fileLink and duration
     */
    public Movie createMovie(String name, String rating, String fileLink, int duration) {

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO movie(name, rating, filelink, duration) values(?,?,?,?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, rating);
                preparedStatement.setString(3, fileLink);
                preparedStatement.setInt(4, duration);
                preparedStatement.executeUpdate();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                Movie movie = new Movie(id, name, rating, fileLink, duration);
                return movie;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes a movie, by taking the movieId
     * @param id
     */
    public void deleteMovie(int id) {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM Movie WHERE Id =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not delete movie");
            }
        } catch (SQLException throwables) {
            errorHandling.deleteMovieDAOError();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the name of a movie if a match is found
     * @param movie
     */
    public void editMovie(Movie movie) {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Movie SET name=?, rating=?, filelink=?, lastView=? WHERE Id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setString(2, movie.getRating());
            preparedStatement.setString(3, movie.getFileLink());
            preparedStatement.setString(4, movie.getLastView());
            preparedStatement.setInt(5, movie.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit movie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
