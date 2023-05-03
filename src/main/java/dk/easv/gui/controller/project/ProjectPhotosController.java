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
    private String projectName, businessType, projectLocation;
    private int columnIndex = 0, rowIndex = 0;
    private LocalDate projectDate;
    @FXML
    private Button addBtn, nextBtn;
    @FXML
    private GridPane gridPane;
    public void setFields(String projectName, String businessType, String projectLocation, LocalDate projectDate) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;

        System.out.println("Project name: " +projectName
                +"\nBusiness type: " +businessType
                +"\nProject location: " +projectLocation
                +"\nProject date: " +projectDate
        );
    }

    public void addImage(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(gridPane.getScene().getWindow());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            ImageView imageView = new ImageView(image);

            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            gridPane.add(imageView, columnIndex, rowIndex);

            columnIndex++;
            if (columnIndex >= gridPane.getColumnCount()) {
                columnIndex = 0;
                rowIndex++;
            }
        }
    }

    public void nextStep(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP6.getView()));
            Parent root = loader.load();

            ProjectFinalController projectFinalController = loader.getController();
            projectFinalController.setFields(projectName, businessType, projectLocation, projectDate);

            Stage window = (Stage) nextBtn.getScene().getWindow();
            window.setTitle("Step xxx");
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
