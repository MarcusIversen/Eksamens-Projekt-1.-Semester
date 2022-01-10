package gui.controller;

import gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddCategoryController {

    @FXML
    private TextField txtFieldNewCategory;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;

    private CategoryModel categoryModel;

    /**
     * Constructor for AddCategoryController
     * adds CategoryModel
     * @throws SQLException
     */
    public AddCategoryController() throws SQLException {
        this.categoryModel = new CategoryModel();
    }

    /**
     * Closes the create addCategory window.
     */
    public void cancelNewCategoryButton() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Tries to create the new category with the given name, cannot be null.
     * @throws SQLException
     */
    public void createCategoryBtn() throws SQLException {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        if(txtFieldNewCategory != null && !txtFieldNewCategory.getText().isEmpty()){
            categoryModel.createCategory(txtFieldNewCategory.getText());
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText("No name is giving to the category");
            alert.setContentText("To add a category, please enter a name first");
            alert.showAndWait();
        }
        stage.close();
    }
}
