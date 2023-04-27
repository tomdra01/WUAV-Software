package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.AdminModel;
import dk.easv.gui.model.TechnicianModel;
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
    @FXML
    private BorderPane loginPane;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXPasswordField passwordField;
    private TechnicianModel technicianModel;
    private AdminModel adminModel;

    /**
     * Allows user to login.
     */
    public void login() throws IOException {
        String inputUsername = nameField.getText();
        String inputPassword = passwordField.getText();

        if (technicianModel.isValid(inputUsername, inputPassword)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.TECHNICIAN.getPath()));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("WUAV - technician");
            stage.show();

            Stage currentStage = (Stage) loginPane.getScene().getWindow();
            currentStage.close();
        }

        else if (adminModel.isValid(inputUsername, inputPassword)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.ADMIN.getPath()));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("WUAV - admin");
            stage.show();

            Stage currentStage = (Stage) loginPane.getScene().getWindow();
            currentStage.close();
        }

        else PopupUtil.showAlert("Incorrect login", "Incorrect username or password", Alert.AlertType.WARNING);
    }

    /**
     * Allows user to login by pressing "Enter".
     */
    public void loginEnter(){
        nameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {login();} catch (IOException e) {throw new RuntimeException(e);}
            }
        });

        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {login();} catch (IOException e) {throw new RuntimeException(e);}
            }
        });
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        technicianModel = new TechnicianModel();
        adminModel = new AdminModel();

        loginEnter();
    }
}