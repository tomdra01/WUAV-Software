package dk.easv.gui.controller.project;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.User;
import dk.easv.gui.util.PopupUtil;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.RefreshPropertiesSingleton;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private byte[] projectPhoto1, projectPhoto2, projectPhoto3;
    private Image img1, img2, img3;
    private ProjectModel projectModel;
    private BorderPane mainPane = RefreshPropertiesSingleton.getInstance().getMainPane();
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
        this.ProjectDescription = projectDescription;

        pLocationField.setText(projectLocation);
        pDatePicker.setValue(projectDate);
    }

    public void setImages(Image img1, Image img2, Image img3, byte[] projectPhoto1, byte[] projectPhoto2, byte[] projectPhoto3){
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.projectPhoto1 = projectPhoto1;
        this.projectPhoto2 = projectPhoto2;
        this.projectPhoto3 = projectPhoto3;
    }

    public void nextStep() {
        projectLocation = pLocationField.getText();
        projectDate = pDatePicker.getValue();

        if (projectLocation != null && !projectLocation.isEmpty() && projectDate != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP3.getView()));
                Parent root = loader.load();

                ProjectStep3Controller projectStep3 = loader.getController();
                projectStep3.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, ProjectDescription);
                projectStep3.setImages(img1, img2, img3, projectPhoto1, projectPhoto2, projectPhoto3);
                projectStep3.setModel(projectModel);
                projectStep3.setUser(user);

                Stage window = (Stage) nextStepBtn.getScene().getWindow();
                window.setTitle("Step 3");
                Scene scene = new Scene(root);
                window.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException("Failed to proceed to Step 3", e);
            }
        }
        else PopupUtil.showAlert("Fields empty", "Please fill out all the fields", Alert.AlertType.INFORMATION);
    }

    public void previousStep() {
        projectLocation = pLocationField.getText();
        projectDate = pDatePicker.getValue();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP1.getView()));
            Parent root = loader.load();

            ProjectStep1Controller projectStep1 = loader.getController();
            projectStep1.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, ProjectDescription);
            projectStep1.setImages(img1, img2, img3, projectPhoto1, projectPhoto2, projectPhoto3);
            projectStep1.setModel(projectModel);
            projectStep1.setUser(user);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Step 1");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException("Failed to proceed to Step 1", e);
        }
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
