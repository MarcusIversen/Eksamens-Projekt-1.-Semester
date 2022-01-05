package dal;

import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import java.sql.*;
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

    /**
     * Create delen af vores C.R.U.D.
     * Her gøres det muligt at oprette en ny movie inde i vores database.
     * @param name
     * @param rating
     * @param filelink
     * @param lastview
     * @return
     */

    public Movie createMovie(String name, String rating, String filelink, String lastview) {

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO movie(name, rating, filelink, lastview) values(?,?,?,?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, rating);
                preparedStatement.setString(3, filelink);
                preparedStatement.setString(4, lastview);
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
                Movie movie = new Movie(id, name, rating, filelink, lastview);
                return movie;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Read delen af vores C.R.U.D.
     * Her bliver der lavet en liste, som henter alle de film vi har i databasen ned
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
                    String lastview = resultset.getString("lastview");


                    Movie movie = new Movie(id, name, rating, filelink, lastview);
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
     * Update delen af vores C.R.U.D.
     * Her kan du opdatere/ændre din films data.
     * @param movie
     */

    public void editMovie(Movie movie) {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Movie SET name=?, rating=?, fileleink=?, lastview=? WHERE Id=?;";
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
     * Delete delen af vores C.R.U.D.
     * Her bliver det gjort muligt at slette en film fra databasen, filmen slettes udfra dets id.
     * @param movie
     */

    public void deleteMovie(Movie movie) {
            try (Connection connection = databaseConnector.getConnection()) {
                String sql = "DELETE FROM Movie WHERE Id =?;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, movie.getId());
                if (preparedStatement.executeUpdate() != 1) {
                    throw new Exception("Could not delete movie");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {

    }
}
