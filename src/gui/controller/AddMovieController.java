package gui.controller;

import be.Category;
import gui.model.MovieModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    private ComboBox cbProof;

    private MovieModel movieModel = new MovieModel();
    private MediaPlayer mediaPlayer;
    private ObservableList<Category> categories;
    private String selectedCategory;

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
        File selectedFile = fileChooser.showOpenDialog(null);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Movies files", "*.mp4", "*.mpeg4"));
        Media f = new Media(selectedFile.toURI().toString());
        if (selectedFile != null){
            Media media = new Media(new File(selectedFile.getAbsolutePath()).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            txtFieldFile.appendText("data/" + selectedFile.getName());
            mediaPlayer.setOnReady(() -> {
                String timeInSeconds = String.format("%1.0f", mediaPlayer.getMedia().getDuration().toSeconds());
                int minuts = Integer.parseInt(timeInSeconds) / 60;
                int seconds = Integer.parseInt(timeInSeconds) % 60;
                if (10 > seconds) {
                    txtFieldDuration.setText(minuts + ":0" + seconds);
                } else {
                    txtFieldDuration.setText(minuts + ":" + seconds);
                }
            });
        }else {
            System.out.println("File is invalid");
        }
    }

    /**
     * Initialize the combo box to listen to when a new item is selected.
     */
    private void selectedCategory() {
        cbProof.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            selectedCategory = (String) newValue;
            System.out.println("Selected category: " + selectedCategory);
        }));
    }

    /**
     * Assign the category combo box to have the specified categories.
     *
     * @param categories The genres to add.
     */
    public void setCategoryComboBox(ObservableList<Category> categories) {
        this.categories = categories;
        cbProof.getItems().clear();
        for (Category cat : categories)
            cbProof.getItems().add(cat.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedCategory();
    }
}
