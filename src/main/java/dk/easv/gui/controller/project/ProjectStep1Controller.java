package dk.easv.gui.controller.project;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import dk.easv.gui.util.ViewType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
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
public class ProjectStep1Controller implements Initializable {
    @FXML private Button nextStepBtn;
    @FXML private JFXTextField pNameField;
    @FXML private JFXComboBox<String> pBusinessComboBox;
    private BorderPane borderPane;
    private String projectName, businessType, projectLocation, projectDescription;
    private LocalDate projectDate;
    private byte[] projectDrawing;
    private ProjectModel projectModel;

    public void setModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    public void setProject(String projectName, String businessType, String projectLocation, LocalDate projectDate, byte[] projectDrawing, String projectDescription) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;
        this.projectDrawing = projectDrawing;
        this.projectDescription = projectDescription;

        // holding items when you go step back
        pNameField.setText(projectName);
        pBusinessComboBox.setValue(businessType);
    }

    public void nextStep() {
        projectName = pNameField.getText();
        businessType = pBusinessComboBox.getValue();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP2.getView()));
            Parent root = loader.load();

            ProjectStep2Controller projectStep2 = loader.getController();
            projectStep2.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            projectStep2.setModel(projectModel);

            Stage window = (Stage) nextStepBtn.getScene().getWindow();
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
        pBusinessComboBox.setItems(FXCollections.observableArrayList("Consumer", "Corporate & Government"));
    }
}
