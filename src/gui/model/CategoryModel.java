package gui.model;

import be.Category;
import bll.CategoryManager;

import java.sql.SQLException;
import java.util.List;

public class CategoryModel {

    private CategoryManager categoryManager;

    /**
     * Constructor for CategoryModel
     */
    public CategoryModel() throws SQLException {
        categoryManager = new CategoryManager();
    }

    public List<Category> getCategories() throws SQLException {
        List<Category> allCategories = categoryManager.getCategories();
        return allCategories;
    }

    public void createCategory(String name) throws SQLException {
        categoryManager.createCategory(name);
    }

    public void deleteCategory(int id) throws SQLException {
        categoryManager.deleteCategory(id);
    }

    public void editCategory(Category category) throws SQLException {
        categoryManager.editCategory(category);
    }

    public void addMovieToCategory(int categoryId, int movieId) throws SQLException {
        categoryManager.addMovieToCategory(categoryId, movieId);
    }

    public void removeFromCategory(int categoryId, int movieId) throws SQLException {
        categoryManager.removeFromCategory(categoryId, movieId);
    }

}
