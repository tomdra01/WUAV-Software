package dk.easv.gui.controller.documentation;

// imports
import com.jfoenix.controls.JFXToggleButton;
import dk.easv.be.Project;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import dk.easv.gui.util.ProjectDisplay;
import dk.easv.gui.util.ViewType;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

// java imports
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ExternalDocumentationController implements Initializable {
    @FXML private BorderPane currentNode;
    @FXML private Button deleteButton;
    @FXML private JFXToggleButton externalSwitchBtn;
    @FXML private ImageView image1, image2, image3;
    private BorderPane borderPane;
    private ProjectModel projectModel;
    private ProjectDisplay projectDisplay;
    private Project project;

    public void setProject(Project project){
        this.project=project;
        externalSwitchBtn.setSelected(true);

        try {
            List<byte[]> images = projectModel.getProjectImages(project.getId());
            if (images.size() > 0) {
                image1.setImage(new Image(new ByteArrayInputStream(images.get(0))));
            }
            if (images.size() > 1) {
                image2.setImage(new Image(new ByteArrayInputStream(images.get(1))));
            }
            if (images.size() > 2) {
                image3.setImage(new Image(new ByteArrayInputStream(images.get(2))));
            }
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
    public void setModel(ProjectModel projectModel){
        this.projectModel = projectModel;
    }

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void switchToInternalDocumentation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.INTERNAL.getView()));
            Parent root = loader.load();

            InternalDocumentationController documentationController = loader.getController();
            documentationController.setProject(project);
            documentationController.setModel(projectModel);
            documentationController.setPane(borderPane);

            Stage window = (Stage) externalSwitchBtn.getScene().getWindow();
            window.setTitle("Internal documentation window");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            PopupUtil.showAlert("Something went wrong", "Failed to switch to the internal documentation", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void deleteProject() {
        Optional<ButtonType> result = PopupUtil.showConfirmationAlert("Confirm deletion", "Are you sure you want to delete this project?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                projectModel.deleteProject(project);
            } catch (DatabaseException e) {
                PopupUtil.showAlert("Something went wrong", e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
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
