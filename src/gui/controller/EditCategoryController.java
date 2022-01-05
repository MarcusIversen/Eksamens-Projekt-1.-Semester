package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCategoryController {

    @FXML
    public TextField TextInputPlaylist;
    @FXML
    public Button btnCancel;
    @FXML
    public Button btnEdit;
    @FXML
    public TextField playlistId;
    @FXML
    public Label newCategoryName;


    public void cancelEditCategoryButton() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
