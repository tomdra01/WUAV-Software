package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dk.easv.gui.model.TechnicianModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public void login() throws IOException {
        String inputUsername = nameField.getText();
        String inputPassword = passwordField.getText();
        if (technicianModel.isValidAdmin(inputUsername, inputPassword)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/technician_window.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("WUAV");

            stage.show();

            Stage currentStage = (Stage) loginPane.getScene().getWindow();
            currentStage.close();
        }
        else System.out.println("wrong login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        technicianModel = new TechnicianModel();
    }
}