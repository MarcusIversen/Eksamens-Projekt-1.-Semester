package dal;

import be.Category;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

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

    }

    public void editCategory(){

    }

    public void addMovieToCategory(int categoryId, int movieId){

    }

    public void removeFromCategory(int categoryId, int movieId){

    }

    public static void main(String[] args) throws SQLException{
        CategoryDAO categoryDAO = new CategoryDAO();
        //List<Song> allSongs = playlistDAO.getSongsOnPlaylist(45);
        List<Category> allCategories = categoryDAO.getCategories();
        //categoryDAO.createCategory("Drama");
        System.out.println(allCategories);
        //playlistDAO.getTotalDuration(53);
        //System.out.println();
        //playlistDAO.addSongToPlaylist(45,39);

        //playlistDAO.deleteFromPlaylist(18,23);
        //playlistDAO.deletePlaylist(3);
        //System.out.println(allPlaylist);
    }
}
