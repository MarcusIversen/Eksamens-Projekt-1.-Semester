package bll;

import be.Category;
import be.Movie;
import dal.CategoryDAO;

import java.sql.SQLException;
import java.util.List;

public class CategoryManager {

    private CategoryDAO categoryDAO = new CategoryDAO();

    /**
     * Constructor for CategoryManager
     */
    public CategoryManager(){
    }

    /**
     * Gets the list of category using the getCategory method in categoryDAO.
     * @return a list of categories or an empty list of categories
     */
    public List<Category> getCategories(){
        return categoryDAO.getCategories();
    }

    /**
     * Creates a category using the createCategory method in categoryDAO
     * @param name
     * @throws SQLException
     */
    public void createCategory(String name) throws SQLException {
        categoryDAO.createCategory(name);
    }

    /**
     * Deletes a category using the deleteCategory methods in categoryDAO
     * @param id
     */
    public void deleteCategory(int id){
        categoryDAO.deleteCategory(id);
    }

    /**
     * Edits a category using the editCategory from categoryDAO
     * @param category
     */
    public void editCategory(Category category){
        categoryDAO.editCategory(category);
    }

    /**
     * Adds a movie to category using addMovieToCategory method in categoryDAO
     * @param categoryId
     * @param movieId
     */
    public void addMovieToCategory(int categoryId, int movieId){
        categoryDAO.addMovieToCategory(categoryId, movieId);
    }

    /**
     * Gets the movies on category using getMoviesOnCategory from categoryDAO
     * @param categoryId
     * @return a list of movies on category or an empty list of movies on categories
     * @throws SQLException
     */
    public List<Movie> getMoviesOnCategory(int categoryId) throws SQLException {
        return categoryDAO.getMoviesOnCategory(categoryId);
    }

    /**
     * Deletes movie from category using deleteFromCategory from categoryDAO
     * @param categoryId
     * @param movieId
     */
    public void deleteFromCategory(int categoryId, int movieId){
        categoryDAO.deleteFromCategory(categoryId, movieId);
    }
}
