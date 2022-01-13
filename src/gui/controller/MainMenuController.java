package gui.controller;

import be.Category;
import be.ErrorHandling;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    
    @FXML
    private Button btnSearchBar;

    @FXML
    private TextField tfSearchBar;

    @FXML
    private TableView<Movie> tvMovies;
    @FXML
    private TableView<Category> tvCategories;
    @FXML
    private TableView<Movie> tvMoviesOnCategory;

    @FXML
    private TableColumn tcMovieRatingOnCategory;
    @FXML
    private TableColumn tcMoviesOnCategory;
    @FXML
    private TableColumn tcName;
    @FXML
    private TableColumn tcNumberOfMoviesOnCategory;
    @FXML
    private TableColumn tcDuration;

    @FXML
    private TableColumn<Movie, String> tcMovieRating;
    @FXML
    private TableColumn<Movie, String> tcNameOnMovie;
    @FXML
    private TableColumn<Movie, String> tcLastViewed;

    private MovieModel movieModel;
    private CategoryModel categoryModel;
    private ErrorHandling errorHandling;

    public ObservableList<Movie> allMovies = FXCollections.observableArrayList();
    private ObservableList<Category> allCategories = FXCollections.observableArrayList();
    private ObservableList<Movie> allMoviesOnCategories = FXCollections.observableArrayList();

    private ObservableList<Movie> searchData = FXCollections.observableArrayList();

    private Category selectedCategory;
    private Movie selectedMovie;
    private Movie selectedMovieOnCategory;

    private boolean hasSearched = true;

    /**
     * Constructor for MainMenuController.
     * @throws SQLException
     */
    public MainMenuController(){
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
        errorHandling = new ErrorHandling();
    }

    /**
     * Initialize the main view methods
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        selectedCategory();
        selectedMovie();
        selectedMovieOnCategory();
    }

    /**
     * Initialize the 3 different tables used
     */
    public void initializeTable() {
        tcMovieRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tcNameOnMovie.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcLastViewed.setCellValueFactory(new PropertyValueFactory<>("lastView"));
        tcDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        try {
            allMovies = FXCollections.observableList(movieModel.getMovies());
            tableViewLoadMovies(allMovies);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcMovieRatingOnCategory.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tcNumberOfMoviesOnCategory.setCellValueFactory(new PropertyValueFactory<>("movieCount"));
        try {
            allCategories = FXCollections.observableList(categoryModel.getCategories());
            tableViewLoadCategories(allCategories);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loading table view categories
     * @param allCategories
     */
    private void tableViewLoadCategories(ObservableList<Category> allCategories) {
        tvCategories.setItems(getCategoryData());
    }

    /**
     * Loading table view movies
     * @param allMovies
     */
    public void tableViewLoadMovies(ObservableList<Movie> allMovies) {
        tvMovies.setItems(getMoviesData());
    }

    /**
     * Loading table view movies on categories
     * @param allMoviesOnCategories
     */
    private void tableViewMoviesOnCategories(ObservableList<Movie> allMoviesOnCategories) {
        tvMoviesOnCategory.setItems(getMoviesOnCategoriesData());
    }

    /**
     * returns the allCategories list
     * @return
     */
    private ObservableList<Category> getCategoryData() {
        return allCategories;
    }

    /**
     * returns the allMovies list
     * @return
     */
    public ObservableList<Movie> getMoviesData() {
        return allMovies;
    }

    /**
     * returns the allMoviesOnCategories list
     * @return
     */
    private ObservableList<Movie> getMoviesOnCategoriesData() {
        return allMoviesOnCategories;
    }

    /**
     * Loads the tableview for the movies, when search is pressed.
     * @param searchData
     */
    private void searchTableViewLoad(ObservableList<Movie> searchData) {
        tvMovies.setItems(getSearchData());
    }

    /**
     * @return searchData;
     */
    public ObservableList<Movie> getSearchData() {
        return searchData;
    }


    public void closeTheAppButton() {
        Platform.exit();
    }

    /**
     * Makes the tableColumn show the movies on each category
     */
    public void seeMoviesOnCategories() {
        tcMoviesOnCategory.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allMoviesOnCategories = FXCollections.observableList(categoryModel.getMoviesOnCategory(selectedCategory.getId()));
            tableViewMoviesOnCategories(allMoviesOnCategories);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Makes you able to add more categories to the list
     * @throws IOException
     */
    public void goAddCategory() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AddCategory.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Category");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnHiding(event ->
        {
            try {
                allCategories = FXCollections.observableList(categoryModel.getCategories());
                tableViewLoadCategories(allCategories);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Makes you able to add a movie to the database and the tableColumn
     * @throws IOException
     */
    public void goAddMovie() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/AddMovie.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add movie");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnHiding(event ->
        {
            try {
                allMovies = FXCollections.observableList(movieModel.getMovies());
                tableViewLoadMovies(allMovies);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Makes you able to edit the selected category name
     */
    public void goEditCategory() {
        if (selectedCategory != null) {
            Category selectedCategory = (Category) tvCategories.getSelectionModel().getSelectedItem();
            FXMLLoader parent = new FXMLLoader(getClass().getResource("/gui/view/EditCategory.fxml"));
            Scene mainWindowScene = null;
            try {
                mainWindowScene = new Scene(parent.load());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage editCategoryStage;
            editCategoryStage = new Stage();
            editCategoryStage.setScene(mainWindowScene);
            EditCategoryController editCategoryController = parent.getController();
            editCategoryController.setSelectedCategory(selectedCategory);
            editCategoryStage.show();
            editCategoryStage.setOnHiding(event ->
            {
                try {
                    allCategories = FXCollections.observableList(categoryModel.getCategories());
                    tableViewLoadCategories(allCategories);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            errorHandling.editCategoryError();
        }

    }

    /**
     * Makes you able to edit the selected movie title, filepath etc
     */
    public void goEditMovie() {
        if (selectedMovie != null) {
            Movie selectedMovie = tvMovies.getSelectionModel().getSelectedItem();

            FXMLLoader parent = new FXMLLoader(getClass().getResource("/gui/view/EditMovie.fxml"));
            Scene mainWindowScene = null;
            try {
                mainWindowScene = new Scene(parent.load());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage editMovieStage;
            editMovieStage = new Stage();
            editMovieStage.setScene(mainWindowScene);
            EditMovieController editMovieController = parent.getController();
            editMovieController.setSelectedMovie(selectedMovie);
            editMovieStage.show();
            editMovieStage.setOnHiding(event ->
            {
                try {
                    allMovies = FXCollections.observableList(movieModel.getMovies());
                    tableViewLoadMovies(allMovies);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            errorHandling.editMovieError();
        }
    }

    /**
     * deletes the selected movie.
     * Gives you a warning message
     */
    public void deleteMovie(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete movie");
        alert.setContentText(" To delete a movie, remove it from all categories first!! \n Are you sure you want " +
                "to delete this movie?");
        if (selectedMovie != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedMovie();
                movieModel.deleteMovie(selectedMovie.getId());
            } else {
                return;
            }
            try {
                allMovies = FXCollections.observableList(movieModel.getMovies());
                tableViewLoadMovies(allMovies);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            errorHandling.deleteMovieError();
        }
    }

    /**
     * deletes the selected category
     * shows a warning message
     */
    public void deleteCategory(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete category");
        alert.setContentText(" Remove all movies from selected category to delete!! \n Are you sure you want " +
                "to delete this movie?");
        if (selectedCategory != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedCategory();
                categoryModel.deleteCategory(selectedCategory.getId());
            } else {
                return;
            }
            try {
                allCategories = FXCollections.observableList(categoryModel.getCategories());
                tableViewLoadCategories(allCategories);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            errorHandling.deleteCategoryError();
        }

    }

    /**
     * deletes the selected movie from the category list.
     * Gives no warning
     */
    public void deleteMovieInCategory() {

        if (selectedCategory != null && selectedMovieOnCategory != null) {
            try {
                int index = tvMoviesOnCategory.getSelectionModel().getFocusedIndex();
                categoryModel.deleteFromCategory(selectedCategory.getId(), selectedMovieOnCategory.getId());
                reloadMoviesOnCategory();
                reloadCategoryTable();
                tvMoviesOnCategory.getSelectionModel().select(index > 0 ? index - 1 : index);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }else{
            errorHandling.deleteMovieInCategoryError();
        }
    }

    /**
     * Add a movie from the movie list to the selected category
     */
    public void addMovieToCategory() {
        if (selectedMovie != null)
            try {
                categoryModel.addMovieToCategory(selectedCategory.getId(), selectedMovie.getId());
                reloadMoviesOnCategory();
                reloadCategoryTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    /**
     * Reloads the movie table to reflect changes
     */
    public void reloadMovieTable() {
        try {
            int index = tvMovies.getSelectionModel().getFocusedIndex();
            this.tvMovies.setItems(FXCollections.observableList(movieModel.getMovies()));
            tvMovies.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Reloads the category table to reflect changes
     */
    public void reloadCategoryTable() {
        try {
            int index = tvCategories.getSelectionModel().getFocusedIndex();
            this.tvCategories.setItems(FXCollections.observableList(categoryModel.getCategories()));
            tvCategories.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    /**
     * reloads the movies on the category in view to reflect changes
     */
    public void reloadMoviesOnCategory() {
        try {
            int index = tvMoviesOnCategory.getSelectionModel().getFocusedIndex();
            this.tvMoviesOnCategory.setItems(FXCollections.observableList(movieModel.getMovies()));
            tvMoviesOnCategory.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Makes you able to select a category from the table
     */
    private void selectedCategory() {
        this.tvCategories.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Category) newValue != null) {
                this.selectedCategory = (Category) newValue;
                seeMoviesOnCategories();
            }
        }));
    }

    /**
     * Makes you able to select a movie on the category from the table
     * If you doubleclick a movie, it updates the last viewed column - also edits the database using the editMovie() method
     */
    private void selectedMovieOnCategory() {
        this.tvMoviesOnCategory.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            this.selectedMovieOnCategory = (Movie) newValue;
            if (selectedMovieOnCategory != null) {
                this.tvMovies.getSelectionModel().clearSelection();
            }
        }));

        this.tvMoviesOnCategory.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && selectedMovieOnCategory != null) {
                try {
                    Date lastView_date = new Date(System.currentTimeMillis());
                    String pattern = "dd/MM/yyyy  HH:mm:ss";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String date = simpleDateFormat.format(lastView_date);
                    selectedMovieOnCategory.setLastView(date);

                    movieModel.editMovie(selectedMovieOnCategory);
                    Desktop.getDesktop().open(new File(selectedMovieOnCategory.getFileLink()));
                    reloadMovieTable();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Makes you able to select a movie
     * If you doubleclick a movie, it updates the last viewed column - also edits the database using the editMovie() method
     */
    private void selectedMovie() {
        this.tvMovies.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Movie) newValue != null) {
                this.selectedMovie = (Movie) newValue;
            }
        }));

        this.tvMovies.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && selectedMovie != null) {
                try {
                    Date lastView_date = new Date(System.currentTimeMillis());
                    String pattern = "dd/MM/yyyy  HH:mm:ss";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String date = simpleDateFormat.format(lastView_date);
                    selectedMovie.setLastView(date);

                    movieModel.editMovie(selectedMovie);
                    Desktop.getDesktop().open(new File(selectedMovie.getFileLink()));
                    reloadMovieTable();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Method that filters the movies, with the text input you write in the textfield, next to the search button.
     * Updates the icon with each press and clears the search on every second click
     */
    public void filterMovies() {
        if (hasSearched == true && !tfSearchBar.getText().equals("")) {
            btnSearchBar.setText("X");
            hasSearched = false;
        } else {
            btnSearchBar.setText("🔍");
            hasSearched = true;
            tfSearchBar.clear();
        }
        try {
            searchData = FXCollections.observableList(movieModel.searchMovie(tfSearchBar.getText()));
            searchTableViewLoad(searchData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes the selected movie on the category and moves its position 1 up
     * Doesn't reflect the changes to the database
     */
    public void btnUp() {
        if (selectedMovieOnCategory != null) {
            try {
                int index = tvMoviesOnCategory.getSelectionModel().getFocusedIndex() - 1;
                int index1 = tvMoviesOnCategory.getSelectionModel().getFocusedIndex() - 0;
                tvMoviesOnCategory.getSelectionModel().select(index);
                Collections.swap(allMoviesOnCategories, index, index1);
                tvMoviesOnCategory.getSelectionModel().select(index);
            } catch (Exception exception) {
                errorHandling.buttonError();
            }
        }
    }

    /**
     * Takes the selected movie on the category and moves its position 1 down
     * Doesn't reflect the changes to the database
     */
    public void btnDown() {
        if (selectedMovieOnCategory != null) {
            try {
                int index = tvMoviesOnCategory.getSelectionModel().getFocusedIndex() + 1;
                int index1 = tvMoviesOnCategory.getSelectionModel().getFocusedIndex() - 0;
                tvMoviesOnCategory.getSelectionModel().select(index);
                Collections.swap(allMoviesOnCategories, index, index1);
                tvMoviesOnCategory.getSelectionModel().select(index);
            } catch (Exception exception) {
                errorHandling.buttonError();
            }
        }
    }

    /**
     * Searches the selectedMovie using the title on IMDB.
     */
    public void searchOnIMDB() {
        String query = selectedMovie == null ? selectedMovieOnCategory.getName() : selectedMovie.getName();
        query = query.replaceAll(" ", "_");
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("https://www.imdb.com/find?q=" + query + "&ref_=nv_sr_sm"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
