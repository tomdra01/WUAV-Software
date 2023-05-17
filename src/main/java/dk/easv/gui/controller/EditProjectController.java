package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.Project;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.gui.model.ProjectModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

// java imports
import java.time.LocalDate;

/**
 *
 * @author tomdra01, mrtng1
 */
public class EditProjectController {
    @FXML private JFXTextField nameField, locationField;
    @FXML private JFXComboBox<String> businessType;
    @FXML private DatePicker dateField;
    @FXML private JFXTextArea descTextField;
    private Project project;
    private ProjectModel projectModel;

    public void setProject(Project project) {
        this.project=project;

        businessType.setItems(FXCollections.observableArrayList("Consumer", "Corporate & Government"));


        nameField.setText(project.getName());
        locationField.setText(project.getLocation());
        businessType.setValue(project.getBusinessType());
        dateField.setValue(project.getDate());
        descTextField.setText(project.getDescription());
    }
    public void setProjectModel(ProjectModel projectModel){
        this.projectModel=projectModel;
    }

    public void editProject() {
        try {
            String newName = nameField.getText();
            String newLocation = locationField.getText();
            String newBusinessType = businessType.getValue();
            LocalDate newDate = dateField.getValue();
            String newDescription = descTextField.getText();

            project.setName(newName);
            project.setLocation(newLocation);
            project.setBusinessType(newBusinessType);
            project.setDate(newDate);
            project.setDescription(newDescription);

            projectModel.updateProject(project);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
