package gui.controller;

import gui.model.MovieModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddMovieController implements Initializable {

    @FXML
    public TextField txtFieldDuration;
    @FXML
    private TextField txtFieldTitle;
    @FXML
    private TextField txtFieldFile;
    @FXML
    private TextField txtFieldRating;
    @FXML
    private Button btnSaveMovie;
    @FXML
    private Button btnCancel;

    private MovieModel movieModel;
    private MediaPlayer mediaPlayer;

    /**
     * Constructor for AddMovieController.
     */
    public AddMovieController(){
        this.movieModel = new MovieModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Pressing the cancel button takes you back to the main window.
     */
    public void cancelNewMovieButton() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Button for saving the newly added movies.
     * Gets the text in each textField
     */
     public void saveMovieButton(){
     String title = txtFieldTitle.getText();
     String rating = txtFieldRating.getText();
     String fileLink = txtFieldFile.getText();
     int duration = (int) mediaPlayer.getMedia().getDuration().toSeconds();

     movieModel.createMovie(title, rating, fileLink, duration);
     Stage stage = (Stage) btnSaveMovie.getScene().getWindow();
     stage.close();
     }

    /**
     * FileChooser for adding a mp4 or mpeg4 file
     */
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
                    txtFieldDuration.setText(minutes + ":0" + seconds);
                } else {
                    txtFieldDuration.setText(minutes + ":" + seconds);
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
