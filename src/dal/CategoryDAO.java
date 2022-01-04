package dal;

import be.Category;
import dal.db.DatabaseConnector;

import java.sql.SQLException;
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

    public List<Category> getCategories(){
        ArrayList<Category> allCategories = new ArrayList<>();

        return allCategories;
    }

    public Category createCategory(){
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

    public static void main(String[] args) {

    }
}
