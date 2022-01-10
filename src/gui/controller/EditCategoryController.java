package gui.controller;

import be.Category;
import com.sun.tools.javac.Main;
import gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditCategoryController {

    @FXML
    private TextField txtFieldEditCategory;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField playlistId;

    private CategoryModel categoryModel = new CategoryModel();

    /**
     * Constructor for the EditCategoryController.
     */
    public EditCategoryController() throws SQLException {
    }

    /**
     * Cancels the cancelEditCategoryButton window and takes us back to the main window.
     * @param actionEvent
     */
    public void cancelEditCategoryButton(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Edits the name of the selected category when pressed.
     * @param actionEvent
     * @throws SQLException
     */
    public void editCategoryButton(ActionEvent actionEvent) throws SQLException {
        String name = txtFieldEditCategory.getText();
        int id = Integer.parseInt(playlistId.getText());
        Category category = new Category(id, name);
        categoryModel.editCategory(category);
        cancelEditCategoryButton(actionEvent);
    }

    /**
     * Sets the setSelected Category and defines what text fields should be changed.
     * @param category
     */
    public void setSelectedCategory(Category category) {
        txtFieldEditCategory.setText(category.getName());
        playlistId.setText(Integer.toString(category.getId()));
    }

}
