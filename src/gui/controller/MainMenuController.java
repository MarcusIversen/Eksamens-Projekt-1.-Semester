package gui.controller;

import be.Category;
import be.Movie;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
    ObservableList<Movie> searchData = FXCollections.observableArrayList();

    private Category selectedCategory;
    private Movie selectedMovie;

    public MainMenuController() throws SQLException {
    }

    /**
     * Initialize the 3 different tables used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        selectedCategory();
        selectedMovie();
    }

    public void initializeTable(){
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
        stage.setOnHiding( event ->
        { try {
            allCategories = FXCollections.observableList(categoryModel.getCategories());
            tableViewLoadCategories(allCategories);
        } catch (Exception e){
            e.printStackTrace();
        } });
    }

    public void goAddMovie() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AddMovie.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add movie");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnHiding( event ->
        { try {
            allMovies = FXCollections.observableList(movieModel.getMovies());
            tableViewLoadMovies(allMovies);
        } catch (Exception e){
            e.printStackTrace();
        } });
    }


    public void goEditCategory(ActionEvent actionEvent) throws IOException {
        if(selectedCategory != null) {
            Category selectedCategory = (Category) tvCategories.getSelectionModel().getSelectedItem();
            FXMLLoader parent = new FXMLLoader(getClass().getResource("/gui/view/EditCategory.fxml"));
            Scene mainWindowScene = null;
            try {
                mainWindowScene = new Scene(parent.load());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage editCategoryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            editCategoryStage = new Stage();
            editCategoryStage.setScene(mainWindowScene);
            EditCategoryController editCategoryController = parent.getController();
            editCategoryController.setSelectedCategory(selectedCategory);
            editCategoryStage.show();
        }else{
            System.out.println("No playlist selected");
        }
        /*
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/EditCategory.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Edit Category");
        stage.setScene(new Scene(root));
        stage.show();
        */
    }

    public void goEditMovie() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/EditMovie.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Edit Movie");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void deleteMovie() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete movie");
        alert.setContentText("Are you sure you want to delete this movie!?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            selectedMovie();
            movieModel.deleteMovie(selectedMovie.getId());
        }else {
            return;
        }
        try{
            allMovies = FXCollections.observableList(movieModel.getMovies());
            tableViewLoadMovies(allMovies);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteMovieInCategory(){

    }

    public void addMovieToCategory() {
        if (selectedMovie != null)
            try {
                categoryModel.addMovieToCategory(selectedCategory.getId(), selectedMovie.getId());
                reloadMovieTable();
                reloadCategoryTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void deleteCategory() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete category");
        alert.setContentText("Are you sure you want to delete this category!?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            selectedCategory();
            categoryModel.deleteCategory(selectedCategory.getId());
        }else {
            return;
        }
        try{
            allCategories = FXCollections.observableList(categoryModel.getCategories());
            tableViewLoadCategories(allCategories);
        } catch (Exception e){
            e.printStackTrace();
        }
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

    /**
     * Reloads the category table
     */
    private void reloadCategoryTable() {
        try {
            int index = tvCategories.getSelectionModel().getFocusedIndex();
            this.tvCategories.setItems(FXCollections.observableList(categoryModel.getCategories()));
            tvCategories.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Makes you able to select a playlist from the table
     */
    private void selectedCategory(){
        this.tvCategories.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Category) newValue != null) {
                this.selectedCategory = (Category) newValue;
            }
        }));
    }

    /**
     * Loads the tableview for the movies, when search is pressed.
     * @param searchData
     */
    private void searchTableViewLoad(ObservableList<Movie> searchData){
        tvMovies.setItems(getSearchData());
    }

    /**
     * Gets the searchdata.
     * @return searchData;
     */
    public ObservableList<Movie> getSearchData() {
        return searchData;
    }

    /**
     * Method that filters the movies, with the text input you write in the textfield, next to the search button.
     */
    public void filterMovies(){
        try{
            searchData = FXCollections.observableList(movieModel.searchMovie(tfSearchBar.getText()));
            searchTableViewLoad(searchData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectedMovie(){
        this.tvMovies.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Movie) newValue != null) {
                this.selectedMovie = (Movie) newValue;
            }
        }));
    }



}
