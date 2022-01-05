package gui.controller;

import be.Category;
import be.Movie;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

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
    private TableColumn tcNameOnMovie;
    @FXML
    private TableColumn tcCategory;
    @FXML
    private TableColumn tcLastViewed;

    private Stage stage = new Stage();
    private MovieModel movieModel = new MovieModel();
    private CategoryModel categoryModel = new CategoryModel();
    private ObservableList<Movie> allMovies = FXCollections.observableArrayList();
    private ObservableList<Category> allCategories = FXCollections.observableArrayList();
    private ObservableList<Movie> allMoviesOnCategories = FXCollections.observableArrayList();

    public MainMenuController() throws SQLException {
    }

    /**
     * Initialize the 3 different tables used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tcMovieRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tcNameOnMovie.setCellValueFactory(new PropertyValueFactory<>("name"));
        //tcLastViewed.setCellValueFactory(new PropertyValueFactory<>("lastview"));
        //TODO make lastView work

        try {
            allMovies = FXCollections.observableList(movieModel.getMovies());
            tableViewLoadMovies(allMovies);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allCategories = FXCollections.observableList(categoryModel.getCategories());
            tableViewLoadCategories(allCategories);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void tableViewLoadCategories(ObservableList<Category> allCategories) {
        tvCategories.setItems(getCategoryData());
    }

    private ObservableList<Category> getCategoryData() {
        return allCategories;
    }

    private void tableViewLoadMovies(ObservableList<Movie> allMovies) {
        tvMovies.setItems(getMoviesData());
    }

    private ObservableList<Movie> getMoviesData() {
        return allMovies;
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
