package dk.easv.gui.controller.project;

// imports
import com.jfoenix.controls.JFXTextArea;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.ViewType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class ProjectStep4Controller implements Initializable {
    @FXML private Button nextStepBtn, previousStepBtn;
    @FXML private JFXTextArea textArea;
    private String projectName, businessType, projectLocation, projectDescription;
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
        this.projectDescription = projectDescription;

        textArea.setText(projectDescription);
    }

    public void nextStep() {
        try {
            projectDescription = textArea.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP5.getView()));
            Parent root = loader.load();

            ProjectStep5Controller projectStep5 = loader.getController();
            projectStep5.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            projectStep5.setModel(projectModel);

            Stage window = (Stage) nextStepBtn.getScene().getWindow();
            window.setTitle("Step 5");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to change the window", e);
        }
    }

    public void previousStep() {
        try {
            projectDescription = textArea.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP3.getView()));
            Parent root = loader.load();

            ProjectStep3Controller projectStep3 = loader.getController();
            projectStep3.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            projectStep3.setModel(projectModel);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Step 3");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to change the window", e);
        }
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
