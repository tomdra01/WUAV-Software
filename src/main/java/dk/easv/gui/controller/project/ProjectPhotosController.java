package dk.easv.gui.controller.project;

// imports
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
public class ProjectPhotosController implements Initializable {
    @FXML private Button addBtn, nextBtn;
    @FXML private ImageView imgSelected1, imgSelected2, imgSelected3;
    private Image image1, image2, image3;
    private String projectName, businessType, projectLocation;
    private LocalDate projectDate;

    public void setFields(String projectName, String businessType, String projectLocation, LocalDate projectDate) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;
    }

    public void addImage(){

    }

    public void nextStep(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP6.getView()));
            Parent root = loader.load();

            ProjectFinalController projectFinalController = loader.getController();
            projectFinalController.setFields(projectName, businessType, projectLocation, projectDate);

            Stage window = (Stage) nextBtn.getScene().getWindow();
            window.setTitle("Step 6");
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
