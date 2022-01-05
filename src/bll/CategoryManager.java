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
    public List<Category> getPlaylist() throws SQLException {
        List<Category> allPlaylist = categoryDAO.getCategories();
        return allPlaylist;
    }

    /**
     * Gets the list of movie on the category's by taking the id
     * Uses the method in CategoryDAO.
     */
    public List<Movie> getSongsOnPlaylist(int id) throws SQLException {
        return categoryDAO.getCategories(id);
    }

}
