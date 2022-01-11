package dal.Interfaces;


import be.Category;
import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAOInterface {

    /**
     * Making a category list, connecting to the database and adding the results to our ArrayList.
     * @return a list of categories or an empty list of categories
     */
    List<Category> getCategories();

    /**
     * Creating a category and inserting/storing the value in our SQL database.
     * @param name
     * @return a Category with a name
     * @throws SQLServerException
     */
    Category createCategory(String name) throws SQLServerException;

    /**
     * Deletes the selected category based on the CategoryId
     * @param id
     */

    void deleteCategory(int id);
    /**
     * Changes the name of the category if a match is found.
     *
     * @param  category a category with the new name, and the original id.
     * @throws SQLException if it cannot connect to the database or something went wrong.
     */
    void editCategory(Category category);

    /**
     * Adds the selected movie to the MoviesInCategory table,
     * which holds the values for both the categoryId and the MovieId.
     * @param categoryId
     * @param movieId
     */
    void addMovieToCategory(int categoryId, int movieId);

    /**
     * Gets an arraylist of movies on the categories by taking the categoryId
     * @param categoryId
     * @return
     * @throws SQLException
     */
    List<Movie> getMoviesOnCategory(int categoryId) throws SQLException;

    /**
     * Deletes a selected movie from category, by selecting a category/taking the categoryId and select a movie/movieId within category
     * @param categoryId
     * @param movieId
     */
    void deleteFromCategory(int categoryId, int movieId);
}
