package dk.easv.gui.controller;

import com.jfoenix.controls.JFXTextField;
import dk.easv.be.User;
import dk.easv.bll.util.PasswordSecurity;
import dk.easv.gui.util.PopupUtil;
import dk.easv.gui.model.UserModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EditUserWindowController {
    @FXML private JFXTextField usernameField, passwordField, passwordConfirmField;
    private User user;
    private UserModel userModel;
    private BorderPane borderPane;

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }
    public void setUserModel(UserModel userModel) {this.userModel = userModel;}
    public void setUser(User user) {
        this.user = user;

        usernameField.setText(user.getUsername());
    }
    public void submitButton() {
        String password = passwordField.getText();
        String passwordConfirm = passwordConfirmField.getText();

        if (!password.equals(passwordConfirm)) {
            PopupUtil.showAlert("Password Mismatch", "The password and confirmation password do not match.", Alert.AlertType.INFORMATION);
            return;
        }
        else {
            user.setUsername(usernameField.getText());
            String hashedPassword = PasswordSecurity.hashPassword(password);
            user.setPassword(hashedPassword);
        }

        try {
            userModel.editUser(user);
        } catch (Exception e) {
            PopupUtil.showAlert("Database Error", "Failed to update the user.", Alert.AlertType.INFORMATION);
        }
    }
}
