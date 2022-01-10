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
    public TextField txtFieldEditCategory;
    @FXML
    public Button btnCancel;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField playlistId;
    @FXML
    public Label newCategoryName;

    private CategoryModel categoryModel = new CategoryModel();

    public EditCategoryController() throws SQLException {
    }

    /**
     *
     * @param actionEvent
     */
    public void cancelEditCategoryButton(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     *
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
     *
     * @param category
     */
    public void setSelectedCategory(Category category) {
        txtFieldEditCategory.setText(category.getName());
        playlistId.setText(Integer.toString(category.getId()));
    }

}
