package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.Log;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.ProjectModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

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
    private Project project;
    private ProjectModel projectModel;
    private User user;

    public void setProjectModel(ProjectModel projectModel){
        this.projectModel=projectModel;
    }
    public void setUser(User user) {this.user = user;}

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
            Log log = new Log("Project edited with id: " +project.getId(), LocalDateTime.now(), user.getId());
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
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
