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

    private CategoryModel categoryModel;

    /**
     *
     * @throws SQLException
     */
    public AddCategoryController() throws SQLException {
        this.categoryModel = new CategoryModel();
    }

    /**
     *
     */
    public void cancelNewCategoryButton() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     *
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
