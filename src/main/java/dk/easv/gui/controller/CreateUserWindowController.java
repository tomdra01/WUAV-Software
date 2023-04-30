package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXComboBox;
import dk.easv.be.roles.ProjectManager;
import dk.easv.be.roles.Salesman;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.GUIException;
import dk.easv.bll.util.PasswordSecurity;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.ProjectManagerModel;
import dk.easv.gui.model.SalesmanModel;
import dk.easv.gui.model.TechnicianModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// java imports
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateUserWindowController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXComboBox<String> jfxComboBox;
    @FXML
    private TextField usernameField, passwordField;
    private TechnicianModel technicianModel;
    private SalesmanModel salesmanModel;
    private ProjectManagerModel projectManagerModel;
    ObservableList<String> userTypes = FXCollections.observableArrayList();

    public void setModel(TechnicianModel technicianModel, ProjectManagerModel projectManagerModel, SalesmanModel salesmanModel) {
        this.technicianModel = technicianModel;
        this.projectManagerModel = projectManagerModel;
        this. salesmanModel = salesmanModel;
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

        // technician
        if(userType.equals("Technician")){
            if(!usernameField.getText().isEmpty()) {
                Technician technician = new Technician(username, hashedPassword);
                try {
                    technicianModel.createTechnician(technician);
                } catch (SQLException e) {
                    throw new GUIException("Failed to create Technician", e);
                }
            }
            else PopupUtil.showAlert("Empty fields", "Please fill in all the fields", Alert.AlertType.INFORMATION);
        }

        // salesman
        else if (userType.equals("Salesman")){
            if(!usernameField.getText().isEmpty()) {
                Salesman salesman = new Salesman(username, hashedPassword);

                try {
                    salesmanModel.createSalesman(salesman);
                } catch (SQLException e) {
                    throw new GUIException("Failed to create Salesman", e);
                }
            }
            else PopupUtil.showAlert("Empty fields", "Please fill in all the fields", Alert.AlertType.INFORMATION);
        }

        // project manager
        else if(userType.equals("Project Manager")){
            if(!usernameField.getText().isEmpty()) {
                ProjectManager projectManager = new ProjectManager(username, hashedPassword);
                try {
                    projectManagerModel.createProjectManager(projectManager);
                } catch (SQLException e) {
                    throw new GUIException("Failed to create Project Manager", e);
                }
            }
            else PopupUtil.showAlert("Empty fields", "Please fill in all the fields", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userTypes.addAll("Technician", "Salesman", "Project Manager");
        jfxComboBox.setItems(userTypes);
    }
}
