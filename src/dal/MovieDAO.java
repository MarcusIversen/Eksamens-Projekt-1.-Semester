package dal;

import be.Category;
import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDAO {

    private final DatabaseConnector databaseConnector;
    private String oldSearchQuery = "";

    /**
     * Making a reference to the databaseConnector, so we can connect to the SQL Database.
     */
    public MovieDAO() throws SQLException {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Create part of C.R.U.D.
     * Method to create a movie in the database.
     *
     * @param name
     * @param rating
     * @param fileLink
     * @return
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
     * Read part of C.R.U.D.
     * Method to read alle the movies in the database.
     *
     * @return
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
                    int duration = resultset.getInt("duration");



                    Movie movie = new Movie(id, name, rating, filelink, duration);
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
     * Update part of C.R.U.D.
     * Method to update/edit movies in the database.
     *
     * @param movie
     */

    public void editMovie(Movie movie) {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Movie SET name=?, rating=?, filelink=?, lastview=? WHERE Id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setString(2, movie.getRating());
            preparedStatement.setString(3, movie.getFileLink());
            preparedStatement.setString(4, movie.getLastView());
            preparedStatement.setInt(5, movie.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not delete movie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete part of C.R.U.D.
     * Method to delete movies from the database.
     *
     * @param
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING MESSAGE");
            alert.setHeaderText("There is still categories assigned to this movie!!");
            alert.setContentText(" To delete a movie, delete it from all categories first!!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        MovieDAO movieDAO = new MovieDAO();
        List<Movie> allMovies = movieDAO.getMovies();
        //movieDAO.createMovie("test", String.valueOf(4.7), "imdb.com", "yesterday");
        System.out.println(allMovies);

    }

}
