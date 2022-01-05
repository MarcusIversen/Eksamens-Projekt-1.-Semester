package gui.controller;

import gui.model.MovieModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainMenuController {

    @FXML
    private Button btnEditCategory;
    @FXML
    private Button btnDeleteCategory;
    @FXML
    private Button btnDeleteMovieFromCategory;
    @FXML
    private Button btnUp;
    @FXML
    private Button btnDown;
    @FXML
    private Button btnAddMovie;
    @FXML
    private Button btnEditMovie;
    @FXML
    private Button btnDeleteMovie;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnLeftArrow;
    @FXML
    private Button btnAddCategory;
    @FXML
    private Button btnSearchBar;

    @FXML
    private Label labelCategories;
    @FXML
    private Label labelMoviesOnCategory;
    @FXML
    private Label labelMovies;
    @FXML
    private Label labelFilter;

    @FXML
    private TextField tfSearchBar;

    @FXML
    private TableView tvMovies;
    @FXML
    private TableView tvCategories;
    @FXML
    private TableView tvMoviesOnCategory;

    @FXML
    private TableColumn tcMovieRatingOnCategory;
    @FXML
    private TableColumn tcMoviesOnCategory;
    @FXML
    private TableColumn tcName;
    @FXML
    private TableColumn tcNumberOfMoviesOnCategory;
    @FXML
    private TableColumn tcMovieRating;
    @FXML
    private TableColumn tcTitle;
    @FXML
    private TableColumn tcCategory;
    @FXML
    private TableColumn tcLastViewed;

    private Stage stage = new Stage();
    private MovieModel movieModel = new MovieModel();

    public MainMenuController() throws SQLException {
    }

    public void closeTheAppButton() {
        Platform.exit();
    }

    public void goAddCategory() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AddCategory.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Category");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void goAddMovie() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AddMovie.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add movie");
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

    /**
     * Reloads the song table
     */
    public void reloadMovieTable() throws Exception {
        try {
            int index = tvMovies.getSelectionModel().getFocusedIndex();
            this.tvMovies.setItems(FXCollections.observableList(movieModel.getMovies()));
            tvMovies.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
