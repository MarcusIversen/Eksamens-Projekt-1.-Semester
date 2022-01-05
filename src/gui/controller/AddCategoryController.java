package gui.controller;

import gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddCategoryController {

    @FXML
    public TextField txtFieldNewCategory;
    @FXML
    public Button BackMainMenu;
    @FXML
    public Button editButton;
    @FXML
    public TextField playlistId;
    @FXML
    public Label NewSongTitle;
    @FXML
    public Button btnCancel;
    @FXML
    public Button btnSave;

    private CategoryModel categoryModel = new CategoryModel();

    public AddCategoryController() throws SQLException {
    }


    public void cancelNewCategoryButton() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }


    public void createCategoryBtn() throws SQLException {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        if(txtFieldNewCategory != null && !txtFieldNewCategory.getText().isEmpty()){
            categoryModel.createCategory(txtFieldNewCategory.getText());
        }else{
            System.out.println("You did not give your category a name");
            //TODO error handling
        }
        stage.close();
    }
}
