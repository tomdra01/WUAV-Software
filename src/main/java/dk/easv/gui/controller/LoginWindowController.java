package dk.easv.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginWindowController {
    @FXML
    private TextField nameField, passwordField;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void handleLogin(javafx.event.ActionEvent actionEvent) throws IOException {

        //login method

        String inputUsername = nameField.getText();
        String inputPassword = passwordField.getText();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main_window.fxml"));
        Parent createEventParent = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(createEventParent);
        stage.setScene(scene);
        stage.setTitle("WUAV");
        stage.show();

        Stage currentStage = (Stage) anchorPane.getScene().getWindow();
        currentStage.close();
    }
}
