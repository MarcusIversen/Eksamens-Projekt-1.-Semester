package gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class OpeningMenuController {

    public Button btnGoToMainMenu;

    public void goMainMenu() throws IOException{
        Stage switcher = (Stage) btnGoToMainMenu.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/gui/view/MainMenu.fxml"));
        Scene scene = new Scene(parent);
        switcher.setScene(scene);
    }

}
