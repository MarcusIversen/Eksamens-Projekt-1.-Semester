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
    public CategoryModel(){
        categoryManager = new CategoryManager();
        categoriesToBeViewed = FXCollections.observableArrayList();
        categoriesToBeViewed.addAll(categoryManager.getCategories());
    }

    /**
     * Gets the list of categories using the getCategories method in CategoryDAO.
     */
    public List<Category> getCategories(){
        List<Category> allCategories = categoryManager.getCategories();
        return allCategories;
    }

    /**
     * Creates a category with a given name using the method in CategoryDAO.
     */
    public void createCategory(String name) throws SQLException {
        categoryManager.createCategory(name);
    }

    /**
     * Deletes a Category by taking the id using the method in CategoryDAO.
     */
    public void deleteCategory(int id){
        categoryManager.deleteCategory(id);
    }

    /**
     * Edits a category by selecting a category using the method in CategoryDAO.
     */
    public void editCategory(Category category){
        categoryManager.editCategory(category);
    }

    /**
     * Adds a movie to the category by taking the categoryId and movieId and using the method in CategoryDAO.
     */
    public void addMovieToCategory(int categoryId, int movieId){
        categoryManager.addMovieToCategory(categoryId, movieId);
    }

    /**
     * Gets the list of movies on the category by taking the categoryId
     * Uses the method in CategoryDAO.
     */
    public List<Movie> getMoviesOnCategory(int categoryId) throws SQLException {
        return categoryManager.getMoviesOnCategory(categoryId);
    }

    /**
     * Deletes a movie from the category by taken categoryId and movieId and using the method in CategoryDAO.
     */
    public void deleteFromCategory(int categoryId, int movieId) throws SQLException {
        categoryManager.deleteFromCategory(categoryId, movieId);
    }
}
