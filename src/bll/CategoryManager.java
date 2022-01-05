package bll;

import be.Category;
import dal.CategoryDAO;

import java.sql.SQLException;
import java.util.List;

public class CategoryManager {

    private CategoryDAO categoryDAO = new CategoryDAO();

    /**
     * Constructor for PlaylistManager
     */
    public CategoryManager() throws SQLException {
    }

    /**
     * Gets the list of category using the getCategory method in categoryDAO.
     */
    public List<Category> getCategories() throws SQLException {
        List<Category> allCategories = categoryDAO.getCategories();
        return allCategories;
    }

    public void createCategory(String name) throws SQLException{
        categoryDAO.createCategory(name);
    }

    public void deleteCategory(int id) throws SQLException{
        CategoryDAO.deleteCategory(id);
    }

}
