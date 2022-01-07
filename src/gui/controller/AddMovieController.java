package gui.controller;

import be.Category;
import gui.model.MovieModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
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
    private Button btnSaveSong;
    @FXML
    private Button btnCancel;

    @FXML
    private ComboBox<String> comboBox;
    ObservableList<String> list = FXCollections.observableArrayList("Test1", "Test2", "Test3");

    private MovieModel movieModel = new MovieModel();
    private MediaPlayer mediaPlayer;

    public AddMovieController() throws SQLException {
    }

    /**
     * Pressing the cancel button takes you back to the main window.
     */
    public void cancelNewMovieButton() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

     public void saveMovieButton() throws Exception {
     String title = txtFieldTitle.getText();
     String rating = txtFieldRating.getText();
     String fileLink = txtFieldFile.getText();
     int duration = (int) mediaPlayer.getMedia().getDuration().toSeconds();

     //todo add combobox
     movieModel.createMovie(title, rating, fileLink, duration);
     Stage stage = (Stage) btnSaveSong.getScene().getWindow();
     stage.close();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(list);
    }
}
