package dk.easv.gui.controller.project;

// imports
import com.jfoenix.controls.JFXTextField;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

// java imports
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectStep2Controller implements Initializable {
    @FXML private Button nextStepBtn, previousStepBtn;
    @FXML private JFXTextField pLocationField;
    @FXML private DatePicker pDatePicker;
    private String projectName, businessType, projectLocation, ProjectDescription;
    private LocalDate projectDate;
    private byte[] projectDrawing;
    private ProjectModel projectModel;

    public void setModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public void setProject(String projectName, String businessType, String projectLocation, LocalDate projectDate, byte[] projectDrawing, String projectDescription) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;
        this.projectDrawing = projectDrawing;
        this.ProjectDescription = projectDescription;

        pLocationField.setText(projectLocation);
        pDatePicker.setValue(projectDate);
    }

    public void nextStep() {
        projectLocation = pLocationField.getText();
        projectDate = pDatePicker.getValue();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP3.getView()));
            Parent root = loader.load();

            ProjectStep3Controller projectStep3 = loader.getController();
            projectStep3.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, ProjectDescription);
            projectStep3.setModel(projectModel);

            Stage window = (Stage) nextStepBtn.getScene().getWindow();
            window.setTitle("Step 3");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to proceed to Step 3", e);
        }
    }

    public void previousStep() {
        projectLocation = pLocationField.getText();
        projectDate = pDatePicker.getValue();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP1.getView()));
            Parent root = loader.load();

            ProjectStep1Controller projectStep1 = loader.getController();
            projectStep1.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, ProjectDescription);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Step 1");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to proceed to Step 1", e);
        }
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}