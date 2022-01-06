package gui.controller;

import be.Movie;
import com.sun.tools.javac.Main;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

public class EditMovieController {


    @FXML
    private Label labelNewEditSongTitle;
    @FXML
    private Label labelNewEditSongCategory;
    @FXML
    private Label labelNewEditSongFile;

    @FXML
    private TextField txtFieldTitle;
    @FXML
    private TextField txtFieldFile;
    @FXML
    private TextField txtFieldFileRating;
    @FXML
    private TextField txtFieldId;

    @FXML
    private Button chooseFileButton;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;

    @FXML
    private ChoiceBox cbProof;

    @FXML
    private Label NewSongTitle;
    @FXML
    private Label labelNewEditSongCategory1;

    private MovieModel movieModel = new MovieModel();
    private MediaPlayer mediaPlayer;
    private MainMenuController mainMenuController = new MainMenuController();

    public EditMovieController() throws SQLException {
    }


    public void cancelEditMovieButton() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Saves the newly added song.
     */
    public void saveBtn() throws Exception {
        String name = txtFieldTitle.getText();
        String rating = txtFieldFileRating.getText();
        String fileLink = txtFieldFile.getText();
        int id = Integer.parseInt(txtFieldId.getText());

        Movie movie = new Movie(id, name, rating, fileLink);
        movieModel.editMovie(movie);
        cancelEditMovieButton();
    }

    /**
     * Gets the values of the selected song.
     */
    public void setSelectedMovie(Movie movie) {
        txtFieldTitle.setText(movie.getName());
        txtFieldFileRating.setText(movie.getRating());
        txtFieldFile.setText(movie.getFileLink());
        txtFieldId.setText(String.valueOf(movie.getId()));
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
