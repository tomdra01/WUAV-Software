package dk.easv.gui.controller.project;

// imports
import com.jfoenix.controls.JFXTextArea;
import dk.easv.bll.exception.GUIException;
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
public class ProjectDescriptionController implements Initializable {
    @FXML private JFXTextArea textArea;
    @FXML private Button nextBtn;
    private String projectName, businessType, projectLocation;
    private LocalDate projectDate;
    public void setFields(String projectName, String businessType, String projectLocation, LocalDate projectDate) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;
    }

    public void nextStep() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP5.getView()));
            Parent root = loader.load();

            ProjectPhotosController projectPhotosController = loader.getController();
            projectPhotosController.setFields(projectName, businessType, projectLocation, projectDate);

            Stage window = (Stage) nextBtn.getScene().getWindow();
            window.setTitle("Step 5");
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
