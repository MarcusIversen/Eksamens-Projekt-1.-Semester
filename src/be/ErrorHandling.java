package be;

import dal.db.DatabaseConnector;
import javafx.scene.control.Alert;

public class ErrorHandling {

    private final DatabaseConnector databaseConnector;

    /**
     * Making a reference to the databaseConnector, so we can connect to the SQL Database.
     */
    public ErrorHandling(){
        databaseConnector = new DatabaseConnector();
    }

    public static void connectionError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Couldn't connect to Server");
        alert.setContentText("Check your Connections and make you have access to the Server");
        alert.showAndWait();
    }

    public static void addMovieError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR MESSAGE");
        alert.setHeaderText("No name is giving to the category");
        alert.setContentText("To add a category, please enter a name first");
        alert.showAndWait();
    }

    public static void addFileError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR MESSAGE");
        alert.setHeaderText("Wrong file type is selected");
        alert.setContentText("To add a movie, select a file type ending with .mp4 or mpeg4 first");
        alert.showAndWait();
    }

    public static void editCategoryError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR MESSAGE");
        alert.setHeaderText("No category is selected");
        alert.setContentText("To edit a category, select a category first");
        alert.showAndWait();
    }

    public static void editMovieError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR MESSAGE");
        alert.setHeaderText("No movie is selected");
        alert.setContentText("To edit a movie, select a movie first");
        alert.showAndWait();
    }

    public static void deleteMovieError(){
        Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
        alertDelete.setTitle("ERROR MESSAGE");
        alertDelete.setHeaderText("No movie is selected");
        alertDelete.setContentText("To edit a movie, select a movie first");
        alertDelete.showAndWait();
    }

    public static void deleteCategoryError(){
        Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
        alertDelete.setTitle("ERROR MESSAGE");
        alertDelete.setHeaderText("No category is selected");
        alertDelete.setContentText("To delete a category, select a category first");
        alertDelete.showAndWait();
    }

    public static void deleteMovieInCategoryError(){
        Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
        alertDelete.setTitle("ERROR MESSAGE");
        alertDelete.setHeaderText("No movie is selected");
        alertDelete.setContentText("To delete a movie, select a movie first");
        alertDelete.showAndWait();
    }

    public static void buttonUpError(){
        Alert alertDelete = new Alert(Alert.AlertType.INFORMATION);
        alertDelete.setTitle("ERROR MESSAGE");
        alertDelete.setHeaderText("There is only one movie in category");
        alertDelete.setContentText("Add more movies to change position");
        alertDelete.showAndWait();
    }

    public static void deleteCategoryDAOError(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("There is still movies in the selected category");
        alert.setContentText("Remove all movies from category to delete");
        alert.showAndWait();
    }

    public static void deleteMovieDAOError(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("There is still categories assigned to this movie!!");
        alert.setContentText(" To delete a movie, delete it from all categories first!!");
        alert.showAndWait();
    }

}
