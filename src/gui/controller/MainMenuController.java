package gui.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    private Stage stage = new Stage();

    public void createNewMovieButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/addMovie.fxml"));
        stage.setTitle("New Song");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void closeTheAppButton() {
        Platform.exit();
    }
}
