package gui.controller;

import be.Movie;
import com.sun.tools.javac.Main;
import gui.model.MovieModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TextField txtFieldFileLastViewed;
    @FXML
    private TextField txtFieldFileDuration;

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

    private MovieModel movieModel;
    private MediaPlayer mediaPlayer;
    private MainMenuController mainMenuController;

    public EditMovieController() throws SQLException {
        movieModel = new MovieModel();
        mainMenuController = new MainMenuController();
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
        String lastViewed = txtFieldFileLastViewed.getText();

        Movie movie = new Movie(id, name, rating, fileLink, lastViewed);
        movieModel.editMovie(movie);
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

    /**
     * Gets the values of the selected song.
     */
    public void setSelectedMovie(Movie movie) {
        txtFieldTitle.setText(movie.getName());
        txtFieldFileRating.setText(movie.getRating());
        txtFieldFile.setText(movie.getFileLink());
        txtFieldId.setText(String.valueOf(movie.getId()));
        txtFieldFileLastViewed.setText(movie.getLastView());
        txtFieldFileDuration.setText(String.valueOf(movie.getDuration()));
    }

    public void chooseMP4Button() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Movie files", "*.mp4", "*.mpeg4"));
        fileChooser.setInitialDirectory(new File("data/"));
        File selectedFile = fileChooser.showOpenDialog(null);

        Media f = new Media(selectedFile.toURI().toString());
        if (selectedFile != null){
            Media media = new Media(new File(selectedFile.getAbsolutePath()).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            txtFieldFile.appendText("data/" + selectedFile.getName());
            mediaPlayer.setOnReady(() -> {
                String timeInSeconds = String.format("%1.0f", mediaPlayer.getMedia().getDuration().toSeconds());
                int minutes = Integer.parseInt(timeInSeconds) / 60;
                int seconds = Integer.parseInt(timeInSeconds) % 60;
                if (10 > seconds) {
                    txtFieldFileDuration.setText(minutes + ":0" + seconds);
                } else {
                    txtFieldFileDuration.setText(minutes + ":" + seconds);
                }
            });
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR MESSAGE");
            alert.setHeaderText("Wrong file type is selected");
            alert.setContentText("To add a movie, select a file type ending with .mp4 or mpeg4 first");
            alert.showAndWait();
        }
    }
}
