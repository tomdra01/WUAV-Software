package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.User;
import dk.easv.bll.exception.GUIException;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.controller.users.AdminWindowController;
import dk.easv.gui.controller.users.ProjectManagerWindowController;
import dk.easv.gui.controller.users.SalesmanWindowController;
import dk.easv.gui.controller.users.TechnicianWindowController;
import dk.easv.gui.model.UserModel;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// java imports
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class LoginWindowController implements Initializable {
    @FXML private BorderPane loginPane;
    @FXML private JFXTextField nameField;
    @FXML private JFXPasswordField passwordField;
    private UserModel userModel;

    public void login() throws IOException {
        String inputUsername = nameField.getText();
        String inputPassword = passwordField.getText();

        String userRole = userModel.getUserRole(inputUsername);

        User user = userModel.isValidUser(inputUsername, inputPassword, userRole);
        if (user != null) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent;
            Stage stage;
            Scene scene;
            String windowTitle = "WUAV - " + userRole;

            switch (userRole) {
                case "Admin" -> {
                    fxmlLoader.setLocation(getClass().getResource(ViewType.ADMIN.getView()));
                    parent = fxmlLoader.load();
                    stage = new Stage();
                    scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle(windowTitle);
                    stage.show();
                    AdminWindowController adminWindowController = fxmlLoader.getController();
                    adminWindowController.setModel(userModel);
                    adminWindowController.setUser(user);
                }
                case "Technician" -> {
                    fxmlLoader.setLocation(getClass().getResource(ViewType.TECHNICIAN.getView()));
                    parent = fxmlLoader.load();
                    stage = new Stage();
                    scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle(windowTitle);
                    stage.show();
                    TechnicianWindowController technicianWindowController = fxmlLoader.getController();
                    technicianWindowController.setModel(userModel);
                    technicianWindowController.setUser(user);
                }
                case "Project Manager" -> {
                    fxmlLoader.setLocation(getClass().getResource(ViewType.PROJECT_MANAGER.getView()));
                    parent = fxmlLoader.load();
                    stage = new Stage();
                    scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle(windowTitle);
                    stage.show();
                    ProjectManagerWindowController projectManagerWindowController = fxmlLoader.getController();
                    projectManagerWindowController.setModel(userModel);
                    projectManagerWindowController.setUser(user);
                }
                case "Salesman" -> {
                    fxmlLoader.setLocation(getClass().getResource(ViewType.SALESMAN.getView()));
                    parent = fxmlLoader.load();
                    stage = new Stage();
                    scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle(windowTitle);
                    stage.show();
                    SalesmanWindowController salesmanWindowController = fxmlLoader.getController();
                    salesmanWindowController.setModel(userModel);
                    salesmanWindowController.setUser(user);
                }
            }
            Stage currentStage = (Stage) loginPane.getScene().getWindow();
            currentStage.close();
        } else {
            PopupUtil.showAlert("Incorrect login", "Incorrect username or password", Alert.AlertType.WARNING);
        }
    }

    /**
     * Allows users to login by pressing "Enter".
     */
    public void loginEnter(){
        nameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {login();} catch (IOException e) {throw new GUIException("Failed to login", e);}
            }
        });

        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {login();} catch (IOException e) {throw new GUIException("Failed to login", e);}
            }
        });
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = new UserModel();
        loginEnter();
    }
}