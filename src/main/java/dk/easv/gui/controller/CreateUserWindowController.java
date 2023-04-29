package dk.easv.gui.controller;

import dk.easv.gui.util.BlurEffectUtil;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.ResourceBundle;

public class CreateUserWindowController implements Initializable {
    @FXML
    private BorderPane borderPane;
    @FXML
    private MFXLegacyComboBox comboBox;
    @FXML
    private TextField usernameField, passwordField;


    ObservableList<String> userTypes = FXCollections.observableArrayList(
            "Technician",
            "Salesman",
            "Project Manager"
    );
    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }
    @FXML
    private void submit() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String hashedPassword = hashPassword(password);
        String userType = (String) comboBox.getValue();

        System.out.println("username: "+username + "password: " + hashedPassword + " " + userType);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(userTypes);
    }
}
