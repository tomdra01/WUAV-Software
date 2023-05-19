package dk.easv.gui.controller.documentation;

// imports
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import dk.easv.be.Project;
import dk.easv.gui.util.ProjectDisplay;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// java imports
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class InternalDocumentationController implements Initializable {
    @FXML private JFXToggleButton internalSwitch;
    @FXML private BorderPane currentNode;
    @FXML private Button deleteButton;
    @FXML private ImageView projectDrawing;
    @FXML private JFXTextArea textArea;
    @FXML private Label nameLabel, locationLabel, dateLabel, businessTypeLabel;
    private BorderPane borderPane;
    private ProjectModel projectModel;
    private ProjectDisplay projectDisplay;
    private Project project;

    public void setProject(Project project){
        this.project=project;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(project.getDrawing());
        Image image = new Image(inputStream);
        projectDrawing.setImage(image);

        nameLabel.setText(project.getName());
        locationLabel.setText(project.getLocation());
        dateLabel.setText(String.valueOf(project.getDate()));
        businessTypeLabel.setText(project.getBusinessType());
        textArea.setText(project.getDescription());
    }
    public void setModel(ProjectModel projectModel){
        this.projectModel = projectModel;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void switchToExternalDocumentation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/documentation/external_documentation.fxml"));
            Parent root = loader.load();

            ExternalDocumentationController documentationController = loader.getController();
            documentationController.setModel(projectModel);
            documentationController.setProject(project);
            documentationController.setPane(borderPane);

            Stage window = (Stage) internalSwitch.getScene().getWindow();
            window.setTitle("External");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException("Failed to change the window", e);
        }
    }

    public void deleteProject(){
        Optional<ButtonType> result = PopupUtil.showConfirmationAlert("Confirm deletion", "Are you sure you want to delete this project?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                projectModel.deleteProject(project);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) deleteButton.getScene().getWindow();
            stage.close();
            BlurEffectUtil.removeBlurEffect(borderPane);
        }
    }

    public void closeWindow() {
        BlurEffectUtil.removeBlurEffect(borderPane);
        Stage stage = (Stage) currentNode.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectDisplay = new ProjectDisplay();
    }
}
