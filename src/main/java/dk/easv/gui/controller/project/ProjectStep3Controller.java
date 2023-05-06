package dk.easv.gui.controller.project;

// imports
import dk.easv.bll.exception.GUIException;
import dk.easv.bll.util.ImageByteReader;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// java imports
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectStep3Controller implements Initializable {
    @FXML private Button previousStepBtn, importBtn;
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

    public void importDrawing() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image");

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("image file formats", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = (Stage) importBtn.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                projectDrawing = ImageByteReader.readImage(selectedFile);

                FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP4.getView()));
                Parent root = loader.load();

                ProjectStep4Controller projectStep4 = loader.getController();
                projectStep4.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
                projectStep4.setModel(projectModel);

                Stage window = (Stage) importBtn.getScene().getWindow();
                window.setTitle("Step 4");
                Scene scene = new Scene(root);
                window.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new GUIException("File chooser fail", e);
            }
        }
    }

    public void createDrawing() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.DRAW_INSTALLATION.getView()));
            Parent root = loader.load();

            DrawInstallationController drawInstallationController = loader.getController();
            drawInstallationController.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            drawInstallationController.setModel(projectModel);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Drawing");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to change the window", e);
        }
    }

    public void previousStep() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP2.getView()));
            Parent root = loader.load();

            ProjectStep2Controller projectStep2 = loader.getController();
            projectStep2.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Step 2");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to proceed to Step 2", e);
        }
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
