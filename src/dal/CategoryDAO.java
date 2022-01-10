package dal;

import be.Category;
import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private final DatabaseConnector databaseConnector;

    /**
     * Making a reference to the databaseConnector, so we can connect to the SQL Database.
     */
    public CategoryDAO(){
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Making a category list, connecting to the database and adding the results to our ArrayList.
     * @return a list of categories or an empty list of categories
     */
    public List<Category> getCategories(){
        ArrayList<Category> allCategories = new ArrayList<>();
        try(Connection connection = databaseConnector.getConnection()) {
            String sqlStatement = "SELECT * FROM Category";
            Statement statement = connection.createStatement();

            if (statement.execute(sqlStatement)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("name");

                    Category category = new Category(id, title);
                    category.setMovies(getMoviesOnCategory(category.getId()));
                    allCategories.add(category);
                }
                return allCategories;
            }
        } catch (SQLException exception) {
            System.out.println(exception);
            return null;
        }
        return allCategories;
    }

    /**
     * Creating a category and inserting/storing the value in our SQL database.
     * @param name
     * @return a Category with a name
     * @throws SQLServerException
     */
    public Category createCategory(String name) throws SQLServerException {
        String sql = "INSERT INTO Category (name) VALUES (?);";
        Connection connection = databaseConnector.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int id = 0;
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
            Category category = new Category(id, name);
            return category;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    /**
     * Deletes the selected category based on the CategoryId
     * @param id
     */
    public void deleteCategory(int id) {
        String sql = "DELETE FROM Category WHERE id = ?;";
        try (Connection con = databaseConnector.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING MESSAGE");
            alert.setHeaderText("There is still movies in the selected category");
            alert.setContentText("Remove all movies from category to delete");
            alert.showAndWait();
        }
    }

    /**
     * Changes the name of the category if a match is found.
     *
     * @param  category a category with the new name, and the original id.
     * @throws SQLException if it cannot connect to the database or something went wrong.
     */
    public void editCategory(Category category) {
        String sql = "UPDATE Category SET name=? WHERE id=?;";
        try (Connection con = databaseConnector.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the selected movie to the MoviesInCategory table,
     * which holds the values for both the categoryId and the MovieId.
     * @param categoryId
     * @param movieId
     */
    public void addMovieToCategory(int categoryId, int movieId){
        String sql = "INSERT INTO CatMovie (categoryId, movieId) VALUES (?,?);";
        try (Connection con = databaseConnector.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, categoryId);
            st.setInt(2, movieId);
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Gets an arraylist of movies on the categories by taking the categoryId
     * @param categoryId
     * @return
     * @throws SQLException
     */
    public List<Movie> getMoviesOnCategory(int categoryId) throws SQLException {
        ArrayList<Movie> allCategories = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Movie INNER JOIN CatMovie ON CatMovie.MovieId = movie.id WHERE CatMovie.categoryId = ?;";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, categoryId);
            st.execute();
            ResultSet rs = st.getResultSet();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String rating = rs.getString("rating");
                String fileLink = rs.getString("fileLink");
                if (fileLink != null)
                    allCategories.add(new Movie(id, name, rating, fileLink));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return allCategories;
    }

    /**
     * Deletes a selected movie from category, by selecting a category/taking the categoryId and select a movie/movieId within category
     * @param categoryId
     * @param movieId
     */
    public void deleteFromCategory(int categoryId, int movieId){
        String sql = "DELETE FROM CatMovie WHERE categoryId = ? AND MovieId = ?;";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, categoryId);
            st.setInt(2, movieId);
            st.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
