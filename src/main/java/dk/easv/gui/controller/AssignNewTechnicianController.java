package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXComboBox;
import dk.easv.be.Log;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.model.UserModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// java imports
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 *
 * @author tomdra01, mrtng1
 */
public class AssignNewTechnicianController implements Initializable {
    @FXML private JFXComboBox<Project> projectComboBox;
    @FXML private JFXComboBox<User> technicianComboBox;
    @FXML private Button cancelBtn;
    private UserModel userModel;
    private User user;
    private ProjectModel projectModel;
    private BorderPane mainPane;

    public void setModel(UserModel userModel, ProjectModel projectModel) {
        this.userModel = userModel;
        this.projectModel = projectModel;
        setProjectComboBoxes();
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(mainPane));
    }

    public void assignNewTechnician() {
        if (!projectComboBox.getSelectionModel().isEmpty() && !technicianComboBox.getSelectionModel().isEmpty()){
            try {
                Log log = new Log(technicianComboBox.getValue()+" assigned to "+ projectComboBox.getValue(), LocalDateTime.now(),user.getUsername());
                projectModel.technicianProject(technicianComboBox.getValue(), projectComboBox.getValue());
                projectModel.createLogEntry(log);
            } catch (DatabaseException e) {
                PopupUtil.showAlert("Something went wrong", e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
            closeWindow();
        } else {
            PopupUtil.showAlert("Fields are not selected", "Please select the project and technician", Alert.AlertType.INFORMATION);
        }
    }

    private void setProjectComboBoxes() {
        projectComboBox.setPromptText("Select project");
        projectComboBox.setItems(projectModel.getProjects());

        technicianComboBox.setPromptText("Select technician");
        technicianComboBox.setItems(userModel.getTechnicians());
    }

    public void closeWindow() {
        BlurEffectUtil.removeBlurEffect(mainPane);
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
