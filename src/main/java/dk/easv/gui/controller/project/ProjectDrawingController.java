package dk.easv.gui.controller.project;

// imports
import dk.easv.bll.exception.GUIException;
import dk.easv.bll.util.ImageByteReader;
import dk.easv.gui.controller.DrawInstallationController;
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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectDrawingController implements Initializable {
    @FXML private Button previousStepBtn, importBtn;
     private String projectName, businessType, projectLocation;
    private LocalDate projectDate;
    private ProjectModel projectModel;


    public void setFields(String projectName, String businessType, String projectLocation, LocalDate projectDate) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;
    }
    public void setModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
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
            throw new GUIException("Failed to proceed to Step 2", e);
        }
    }

    public void importDrawing() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image");

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = (Stage) importBtn.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP4.getView()));
                Parent root = loader.load();

                ProjectDescriptionController projectDescriptionController = loader.getController();
                projectDescriptionController.setFields(projectName, businessType, projectLocation, projectDate);
                projectDescriptionController.setModel(projectModel);

                Stage window = (Stage) importBtn.getScene().getWindow();
                window.setTitle("Step ..");
                Scene scene = new Scene(root);
                window.setScene(scene);

                long fileSize = selectedFile.length();
                System.out.println("File size: " + fileSize);

                ImageByteReader imageByteReader = new ImageByteReader();
                byte[] imageData = imageByteReader.readImage(selectedFile);
                System.out.println("Image data length: " + imageData.length);
                System.out.println(imageData);

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
            drawInstallationController.setFields(projectName, businessType, projectLocation, projectDate);
            drawInstallationController.setModel(projectModel);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Drawing");
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
