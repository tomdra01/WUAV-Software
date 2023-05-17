package dk.easv.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.Project;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.gui.model.ProjectModel;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class EditProjectController {
    @FXML private JFXTextField nameField, locationField;
    @FXML private JFXComboBox<String> businessType;
    @FXML private DatePicker dateField;
    @FXML private JFXTextArea descTextField;
    private Project project;
    private ProjectModel projectModel;

    public void setProject(Project project) {
        this.project=project;
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
