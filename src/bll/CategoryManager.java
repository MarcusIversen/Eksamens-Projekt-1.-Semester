package bll;

import be.Category;
import be.Movie;
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
        return categoryDAO.getCategories();
    }

    /**
     *
     * @param name
     * @throws SQLException
     */
    public void createCategory(String name) throws SQLException{
        categoryDAO.createCategory(name);
    }

    /**
     *
     * @param id
     * @throws SQLException
     */
    public void deleteCategory(int id) throws SQLException{
        categoryDAO.deleteCategory(id);
    }

    /**
     *
     * @param category
     * @throws SQLException
     */
    public void editCategory(Category category) throws SQLException{
        categoryDAO.editCategory(category);
    }

    /**
     *
     * @param categoryId
     * @param movieId
     * @throws SQLException
     */
    public void addMovieToCategory(int categoryId, int movieId) throws SQLException{
        categoryDAO.addMovieToCategory(categoryId, movieId);
    }
    
    /**
     *
     * @param categoryId
     * @return
     * @throws SQLException
     */
    public List<Movie> getMoviesOnCategory(int categoryId) throws SQLException {
        return categoryDAO.getMoviesOnCategory(categoryId);
    }

    /**
     *
     * @param categoryId
     * @param movieId
     * @throws SQLException
     */
    public void deleteFromCategory(int categoryId, int movieId) throws SQLException {
        categoryDAO.deleteFromCategory(categoryId, movieId);
    }
}
