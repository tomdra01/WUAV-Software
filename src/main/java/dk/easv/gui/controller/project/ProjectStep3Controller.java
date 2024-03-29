package dk.easv.gui.controller.project;

// imports
import dk.easv.be.User;
import dk.easv.bll.util.ImageByteReader;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.PopupUtil;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
    private byte[] projectPhoto1, projectPhoto2, projectPhoto3;
    private Image img1, img2, img3;
    private ProjectModel projectModel;
    private User user;

    public void setModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setProject(String projectName, String businessType, String projectLocation, LocalDate projectDate, byte[] projectDrawing, String projectDescription) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;
        this.projectDrawing = projectDrawing;
        this.projectDescription = projectDescription;
    }

    public void setImages(Image img1, Image img2, Image img3, byte[] projectPhoto1, byte[] projectPhoto2, byte[] projectPhoto3){
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.projectPhoto1 = projectPhoto1;
        this.projectPhoto2 = projectPhoto2;
        this.projectPhoto3 = projectPhoto3;
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
                projectStep4.setImages(img1, img2, img3, projectPhoto1, projectPhoto2, projectPhoto3);
                projectStep4.setModel(projectModel);
                projectStep4.setUser(user);

                Stage window = (Stage) importBtn.getScene().getWindow();
                window.setTitle("Step 4");
                Scene scene = new Scene(root);
                window.setScene(scene);
            } catch (IOException e) {
                PopupUtil.showAlert("Something went wrong", "Failed to proceed to step 4", Alert.AlertType.ERROR);
                e.printStackTrace();
            } catch (Exception e) {
                PopupUtil.showAlert("Something went wrong", "Failed to open the file chooser", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }
    }

    public void createDrawing() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.DRAW_INSTALLATION.getView()));
            Parent root = loader.load();

            DrawInstallationController drawInstallationController = loader.getController();
            drawInstallationController.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            drawInstallationController.setImages(img1, img2, img3, projectPhoto1, projectPhoto2, projectPhoto3);
            drawInstallationController.setModel(projectModel);
            drawInstallationController.setUser(user);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Drawing");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            PopupUtil.showAlert("Something went wrong", "Failed to proceed to drawing", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void previousStep() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP2.getView()));
            Parent root = loader.load();

            ProjectStep2Controller projectStep2 = loader.getController();
            projectStep2.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            projectStep2.setImages(img1, img2, img3, projectPhoto1, projectPhoto2, projectPhoto3);
            projectStep2.setModel(projectModel);
            projectStep2.setUser(user);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Step 2");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            PopupUtil.showAlert("Something went wrong", "Failed to proceed to step 2", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
