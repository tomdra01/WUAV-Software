package dk.easv.gui.controller.project;

// imports
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.controller.DrawInstallationController;
import dk.easv.gui.util.ViewType;
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
public class ProjectDrawingController implements Initializable {
    @FXML private Button previousStepBtn;
     private String projectName, businessType, projectLocation;
    private LocalDate projectDate;


    public void setFields(String projectName, String businessType, String projectLocation, LocalDate projectDate) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;
    }

    public void previousStep() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP2.getView()));
            Parent root = loader.load();

            ProjectDetailsController projectDetailsController = loader.getController();
            projectDetailsController.setFields(projectName, businessType, projectLocation, projectDate);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Step 2");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to change the window", e);
        }
    }

    public void importDrawing() {
    }

    public void createDrawing() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.DRAW_INSTALLATION.getView()));
            Parent root = loader.load();

            DrawInstallationController drawInstallationController = loader.getController();
            drawInstallationController.setFields(projectName, businessType, projectLocation, projectDate);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Step 2");
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
