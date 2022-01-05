package gui.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainMenuController {

    public Button btnEditCategory;
    public Button btnDeleteCategory;
    public Button btnDeleteMovieFromCategory;
    public Button btnUp;
    public Button btnDown;
    public Button btnNewMovie;
    public Button btnEditMovie;
    public Button btnDeleteMovie;
    public Button btnClose;
    public Button btnLeftArrow;
    public Button btnNewCategory;
    public Button btnSearchBar;

    public Label labelCategories;
    public Label labelMoviesOnCategory;
    public Label labelMovies;
    public Label labelFilter;

    public TextField tfSearchBar;

    public TableView tvMovies;
    public TableView tvCategories;
    public TableView tvMoviesOnCategory;

    public TableColumn tcMovieRatingOnCategory;
    public TableColumn tcMoviesOnCategory;
    public TableColumn tcName;
    public TableColumn tcNumberOfMoviesOnCategory;
    public TableColumn tcMovieRating;
    public TableColumn tcTitle;
    public TableColumn tcCategory;
    public TableColumn tcLastViewed;

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

    public void goNewMovie() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/NewMovie.fxml"));
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
