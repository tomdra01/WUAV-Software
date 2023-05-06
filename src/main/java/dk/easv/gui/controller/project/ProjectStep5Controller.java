package dk.easv.gui.controller.project;

// imports
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
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
public class ProjectStep5Controller implements Initializable {
    @FXML private Button nextStepBtn, previousStepBtn, addImagesBtn;
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
    }

    public void addImages(){

    }

    public void nextStep(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP6.getView()));
            Parent root = loader.load();

            ProjectStepFinalController projectStepFinalController = loader.getController();
            projectStepFinalController.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            projectStepFinalController.setModel(projectModel);

            Stage window = (Stage) nextStepBtn.getScene().getWindow();
            window.setTitle("Step 6");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to change the window", e);
        }
    }

    public void previousStep() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP4.getView()));
            Parent root = loader.load();

            ProjectStep4Controller projectStep4 = loader.getController();
            projectStep4.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            projectStep4.setModel(projectModel);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Step 4");
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
