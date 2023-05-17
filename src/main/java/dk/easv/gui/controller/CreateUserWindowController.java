package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
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
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class CreateUserWindowController implements Initializable {
    @FXML private BorderPane currentNode, borderPane;
    @FXML private JFXComboBox<String> jfxComboBox;
    @FXML private JFXTextField usernameField, passwordField;
    @FXML private Button cancelButton;
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
     * Creates a users.
     */
    public void createUser() {
        String userType = jfxComboBox.getValue();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String hashedPassword = PasswordSecurity.hashPassword(password);

        if (!usernameField.getText().isEmpty()) {
            User user = new User(username, hashedPassword, userType);

            try {
                userModel.createUser(user);
            } catch (DatabaseException e) {
                throw new RuntimeException("Failed to create users in GUI", e);
            }
        } else {
            PopupUtil.showAlert("Empty fields", "Please fill in all the fields", Alert.AlertType.INFORMATION);
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

        cancelButton.setOnAction(event -> closeWindow());
    }
}
