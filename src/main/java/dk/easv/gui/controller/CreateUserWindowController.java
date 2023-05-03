package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.roles.ProjectManager;
import dk.easv.be.roles.Salesman;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.GUIException;
import dk.easv.bll.util.PasswordSecurity;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.UserModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// java imports
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class CreateUserWindowController implements Initializable {

    @FXML
    private BorderPane currentNode, borderPane;
    @FXML
    private JFXComboBox<String> jfxComboBox;
    @FXML
    private JFXTextField usernameField, passwordField;
    @FXML
    private Button cancelButton;
    private UserModel userModel;
    ObservableList<String> userTypes = FXCollections.observableArrayList();

    public void setModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    /**
     * Creates a user.
     */
    public void createUser() {
        String userType = jfxComboBox.getValue();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String hashedPassword = PasswordSecurity.hashPassword(password);

        switch (userType) {
            // technician
            case "Technician" -> {
                if (!usernameField.getText().isEmpty()) {
                    Technician technician = new Technician(username, hashedPassword);
                    try {
                        userModel.createTechnician(technician);
                        closeWindow();
                    } catch (SQLException e) {
                        throw new GUIException("Failed to create Technician", e);
                    }
                } else
                    PopupUtil.showAlert("Empty fields", "Please fill in all the fields", Alert.AlertType.INFORMATION);
            }

            // salesman
            case "Salesman" -> {
                if (!usernameField.getText().isEmpty()) {
                    Salesman salesman = new Salesman(username, hashedPassword);

                    try {
                        userModel.createSalesman(salesman);
                        closeWindow();
                    } catch (SQLException e) {
                        throw new GUIException("Failed to create Salesman", e);
                    }
                } else
                    PopupUtil.showAlert("Empty fields", "Please fill in all the fields", Alert.AlertType.INFORMATION);
            }

            // project manager
            case "Project Manager" -> {
                if (!usernameField.getText().isEmpty()) {
                    ProjectManager projectManager = new ProjectManager(username, hashedPassword);
                    try {
                        userModel.createProjectManager(projectManager);
                        closeWindow();
                    } catch (SQLException e) {
                        throw new GUIException("Failed to create Project Manager", e);
                    }
                } else
                    PopupUtil.showAlert("Empty fields", "Please fill in all the fields", Alert.AlertType.INFORMATION);
            }
        }
    }

    private void closeWindow() {
        BlurEffectUtil.removeBlurEffect(borderPane);
        Stage stage = (Stage) currentNode.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userTypes.addAll("Technician", "Salesman", "Project Manager");
        jfxComboBox.setItems(userTypes);

        cancelButton.setOnAction(event -> {
            closeWindow();
        });
    }
}
