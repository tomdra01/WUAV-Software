package dk.easv.gui.controller.project;

import com.jfoenix.controls.JFXTextField;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProjectDetailsController implements Initializable {
    @FXML private Button nextStepBtn;
    @FXML private Button previousStepBtn;
    @FXML private JFXTextField pLocationField;
    @FXML private DatePicker pDatePicker;
    @FXML private BorderPane currentPane;
    private String projectName, businessType, projectLocation;
    private LocalDate projectDate;

    public void setFields(String projectName, String businessType, String projectLocation, LocalDate projectDate) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;

        pLocationField.setText(projectLocation);
        pDatePicker.setValue(projectDate);
    }

    public void previousStep() {
        projectLocation = pLocationField.getText();
        projectDate = pDatePicker.getValue();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP1.getView()));
            Parent root = loader.load();

            ProjectInfoController projectInfoController = loader.getController();
            projectInfoController.setFields(projectName, businessType, projectLocation, projectDate);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Step 1");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to change the window", e);
        }
    }

    public void nextStep() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP3.getView()));
            Parent root = loader.load();

            ProjectDrawingController projectDrawingController = loader.getController();
            projectDrawingController.setFields(projectName, businessType, projectLocation, projectDate);

            Stage window = (Stage) nextStepBtn.getScene().getWindow();
            window.setTitle("Step 3");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to change the window", e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
