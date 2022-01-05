package gui.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainMenuController {
    private Stage stage = new Stage();

    public void closeTheAppButton() {
        Platform.exit();
    }

    public void goAddCategory() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AddCategory.fxml"));
        Stage stage = new Stage();
        stage.setTitle("New Category");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void goAddMovie() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AddMovie.fxml"));
        stage.setTitle("New Song");
        Stage stage = new Stage();
        stage.setTitle("New Movie");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void goEditCategory() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/EditCategory.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Edit Category");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void goEditMovie() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/EditMovie.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Edit Movie");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void deleteMovie(){

    }

    public void deleteMovieInCategory(){

    }

    public void deleteCategory(){

    }

}
