package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.Log;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.be.UserSingleton;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.gui.util.PopupUtil;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import dk.easv.gui.util.ProjectDisplay;
import dk.easv.gui.util.RefreshPropertiesSingleton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// java imports
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class EditProjectController implements Initializable {
    @FXML private JFXTextField nameField, locationField;
    @FXML private JFXComboBox<String> businessType;
    @FXML private DatePicker dateField;
    @FXML private JFXTextArea descTextField;
    private final HBox projectsHbox = RefreshPropertiesSingleton.getInstance().getProjectsHbox();
    private final JFXTextField searchBar = RefreshPropertiesSingleton.getInstance().getSearchBar();
    private final JFXComboBox<String> filter = RefreshPropertiesSingleton.getInstance().getFilterComboBox();
    private BorderPane mainPane;
    private Project project;
    private ProjectModel projectModel;
    private User user;
    private ProjectDisplay projectDisplay;

    public void setProjectModel(ProjectModel projectModel){
        this.projectModel=projectModel;
    }
    public void setUser(User user) {this.user = user;}

    public void setPane(BorderPane borderPane) {
        this.mainPane = borderPane;
    }

    public void setProject(Project project) {
        this.project = project;
        businessType.setItems(FXCollections.observableArrayList("Consumer", "Corporate & Government"));
        nameField.setText(project.getName());
        locationField.setText(project.getLocation());
        businessType.setValue(project.getBusinessType());
        dateField.setValue(project.getDate());
        descTextField.setText(project.getDescription());
    }

    public void editProject() {
        try {
            user = UserSingleton.getInstance().getUser();
            Log log = new Log("Edited project: " +project.getId(), LocalDateTime.now(), user.getUsername());
            project.setName(nameField.getText());
            project.setLocation(locationField.getText());
            project.setBusinessType(businessType.getValue());
            project.setDate(dateField.getValue());
            project.setDescription(descTextField.getText());

            projectModel.updateProject(project);
            projectModel.createLogEntry(log);
        } catch (DatabaseException e) {
            PopupUtil.showAlert("Something went wrong", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
        closeWindow();
        projectDisplay.showAllProjects(projectsHbox, filter, searchBar, projectModel, mainPane);
    }

    private void closeWindow() {
        BlurEffectUtil.removeBlurEffect(mainPane);
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectDisplay = new ProjectDisplay();
    }
}
