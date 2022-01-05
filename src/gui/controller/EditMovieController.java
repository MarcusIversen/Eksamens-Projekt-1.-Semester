package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditMovieController {


    @FXML
    public Label labelNewEditSongTitle;
    @FXML
    public Label labelNewEditSongCategory;
    @FXML
    public Label labelNewEditSongFile;

    @FXML
    public TextField txtFieldTitle;
    @FXML
    public TextField txtFieldFile;
    @FXML
    public TextField txtFieldFileRating;

    @FXML
    public Button chooseFileButton;
    @FXML
    public Button btnCancel;
    @FXML
    public Button btnSaveSong;

    @FXML
    public ChoiceBox cbProof;

    @FXML
    public Label NewSongTitle;
    @FXML
    public Label labelNewEditSongCategory1;


    public void cancelEditMovieButton() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
