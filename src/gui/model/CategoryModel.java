package gui.model;

import be.Category;
import be.Movie;
import bll.CategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class CategoryModel {

    private ObservableList<Category> categoriesToBeViewed;
    private CategoryManager categoryManager;

    /**
     * Constructor for CategoryModel
     */
    public CategoryModel() throws SQLException {
        categoryManager = new CategoryManager();
        categoriesToBeViewed = FXCollections.observableArrayList();
        categoriesToBeViewed.addAll(categoryManager.getCategories());
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

    public List<Movie> getMoviesOnCategory(int categoryId) throws SQLException {
        return categoryManager.getMoviesOnCategory(categoryId);
    }

    public void deleteFromCategory(int categoryId, int movieId) throws SQLException {
        categoryManager.deleteFromCategory(categoryId, movieId);
    }
}
