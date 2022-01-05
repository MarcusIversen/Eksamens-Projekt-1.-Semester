package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCategoryController {

    @FXML
    public TextField TextInputPlaylist;
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


    public void cancelNewCategoryButton() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
