package gui.controller;

import be.ErrorHandling;
import gui.model.CategoryModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private ErrorHandling errorHandling;

    /**
     * Constructor for AddCategoryController
     * adds CategoryModel
     * @throws SQLException
     */
    public AddCategoryController() {
        categoryModel = new CategoryModel();
        errorHandling = new ErrorHandling();
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
            errorHandling.addMovieError();
        }
        stage.close();
    }
}
