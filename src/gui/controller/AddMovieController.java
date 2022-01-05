package gui.controller;

import gui.model.MovieModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

public class AddMovieController {
    @FXML
    private TextField txtFieldTitle;
    @FXML
    private TextField txtFieldFile;
    @FXML
    private TextField txtFieldRating;
    @FXML
    private TextField txtFieldCategory;
    @FXML
    private Button btnSaveSong;

    private MovieModel movieModel = new MovieModel();
    private MainMenuController mainMenuController = new MainMenuController();
    private MediaPlayer mediaPlayer;

    public AddMovieController() throws SQLException {
    }

    /**
     * Pressing the cancel button takes you back to the main window.
     */
    public void cancelNewSongButton() {
        Stage stage = (Stage) btnSaveSong.getScene().getWindow();
        stage.close();
    }


     public void saveMovieButton() throws Exception {
     String title = txtFieldTitle.getText();
     String rating = txtFieldRating.getText();
     String fileLink = txtFieldFile.getText();

     //todo add combobox
     movieModel.createMovie(title, rating, fileLink);
     mainMenuController.reloadMovieTable();
     Stage stage = (Stage) btnSaveSong.getScene().getWindow();
     stage.close();
     }

    public void chooseMP4Button() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Movies files", "*.mp4", "*.mpeg4"));
        Media f = new Media(selectedFile.toURI().toString());
        if (selectedFile != null){
            Media media = new Media(new File(selectedFile.getAbsolutePath()).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            txtFieldFile.appendText("data/" + selectedFile.getName());
        }else {
            System.out.println("File is invalid");
        }
    }
}
