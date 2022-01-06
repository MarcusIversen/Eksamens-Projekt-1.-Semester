package dal;

import be.Category;
import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private final DatabaseConnector databaseConnector;

    /**
     * Making a reference to the databaseConnector, so we can connect to the SQL Database.
     */
    public CategoryDAO() throws SQLException {
        databaseConnector = new DatabaseConnector();
    }

    public List<Category> getCategories() throws SQLException{
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

    public void deleteCategory(int id){
        String sql = "DELETE FROM Category WHERE id = ?;";
        try (Connection con = databaseConnector.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editCategory(Category category){
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

    public void addMovieToCategory(int categoryId, int movieId) throws SQLException{
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

    public void removeFromCategory(int categoryId, int movieId) throws SQLException{
        String sql = "DELETE FROM CatMovie WHERE categoryId=? AND movieId=?;";
        try (Connection con = databaseConnector.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, categoryId);
            st.setInt(2, movieId);
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

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

    public void deleteFromCategory(int categoryId, int movieId) throws SQLServerException {
        String sql = "DELETE FROM CatMovie WHERE categoryId = ? AND MovieId = ?;";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, categoryId);
            st.setInt(2, movieId);
            st.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } ;
    }

    public static void main(String[] args) throws SQLException{
        CategoryDAO categoryDAO = new CategoryDAO();
        //List<Song> allSongs = playlistDAO.getSongsOnPlaylist(45);
        List<Category> allCategories = categoryDAO.getCategories();
        //categoryDAO.createCategory("Drama");
        System.out.println(allCategories);
        categoryDAO.deleteCategory(2);
        //playlistDAO.getTotalDuration(53);
        //System.out.println();
        //playlistDAO.addSongToPlaylist(45,39);

        //playlistDAO.deleteFromPlaylist(18,23);
        //playlistDAO.deletePlaylist(3);
        //System.out.println(allPlaylist);
    }
}
