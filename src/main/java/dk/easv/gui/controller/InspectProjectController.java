package dk.easv.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.gui.util.ProjectDisplay;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class InspectProjectController implements Initializable {
    @FXML private Button deleteButton, editButton;
    @FXML private JFXTextField nameField, locationField;
    @FXML private JFXComboBox<String> businessType;
    @FXML private DatePicker dateField;
    @FXML private JFXTextArea descTextField;
    private BorderPane borderPane;
    private ProjectModel projectModel;
    private ProjectDisplay projectDisplay;
    private Project project;

    public void setProject(Project project){
        this.project=project;

        //set default values
        businessType.setItems(FXCollections.observableArrayList("Consumer", "Corporate & Government"));
        businessType.setValue(project.getBusinessType());
        nameField.setText(project.getName());
        locationField.setText(project.getLocation());
        dateField.setValue(project.getDate());
        descTextField.setText(project.getDescription());
    }
    public void setModel(ProjectModel projectModel){
        this.projectModel=projectModel;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void deleteProject(){
        Optional<ButtonType> result = PopupUtil.showConfirmationAlert("Confirm deletion", "Are you sure you want to delete this project?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                projectModel.deleteProject(project);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) deleteButton.getScene().getWindow();
            stage.close();
            BlurEffectUtil.removeBlurEffect(borderPane);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
