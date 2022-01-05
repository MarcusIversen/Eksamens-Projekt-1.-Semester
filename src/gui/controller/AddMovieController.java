package gui.controller;

import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMovieController {

    @FXML
    private TextField txtFieldTitle;
    @FXML
    private TextField txtFieldFile;
    @FXML
    private TextField txtFieldRating;
    @FXML
    private Button btnSaveSong;
    @FXML
    private ChoiceBox<String> cbProof;

    private MovieModel movieModel = new MovieModel();

    public AddMovieController() {

    }


    /**
     * Pressing the cancel button takes you back to the main window.
     */
    public void cancelNewSongButton() {
        Stage stage = (Stage) btnSaveSong.getScene().getWindow();
        stage.close();
    }

    public void saveSongButton() {
        String title = txtFieldTitle.getText();
        String url = txtFieldFile.getText();
        //mangler kategori
        String rating = txtFieldRating.getText();

    }

    public void chooseMP4Button() {
    }
}
