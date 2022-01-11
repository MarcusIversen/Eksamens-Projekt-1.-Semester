package gui.controller;

import be.Category;
import gui.model.CategoryModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditCategoryController {

    @FXML
    private TextField txtFieldEditCategory;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField categoryId;

    private CategoryModel categoryModel;

    /**
     * Constructor for the EditCategoryController.
     */
    public EditCategoryController() throws SQLException {
        this.categoryModel = new CategoryModel();
    }

    /**
     * Cancels the cancelEditCategoryButton window and takes us back to the main window.
     */
    public void cancelEditCategoryButton() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Edits the name of the selected category when pressed.
     * Gets the name from the textField and the saves the categoryId.
     * @throws SQLException
     */
    public void editCategoryButton() throws SQLException {
        String name = txtFieldEditCategory.getText();
        int id = Integer.parseInt(categoryId.getText());
        Category category = new Category(id, name);
        categoryModel.editCategory(category);
        cancelEditCategoryButton();
    }

    /**
     * Sets the setSelected Category and defines what text fields should be changed.
     * @param category
     */
    public void setSelectedCategory(Category category) {
        txtFieldEditCategory.setText(category.getName());
        categoryId.setText(Integer.toString(category.getId()));
    }

}
