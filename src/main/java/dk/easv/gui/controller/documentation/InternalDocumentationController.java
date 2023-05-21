package dk.easv.gui.controller.documentation;

// imports
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import dk.easv.be.Log;
import dk.easv.be.Project;
import dk.easv.gui.util.ProjectDisplay;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
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
import javafx.scene.control.Label;
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
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class InternalDocumentationController implements Initializable {
    @FXML private JFXToggleButton internalSwitch;
    @FXML private BorderPane currentNode;
    @FXML private Button printBtn, deleteBtn, closeBtn;
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

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    public void printDocumentation() throws IOException {
        internalSwitch.setVisible(false);
        printBtn.setVisible(false);
        deleteBtn.setVisible(false);
        closeBtn.setVisible(false);

        Scene scene = currentNode.getScene();
        float aspectRatio = (float) scene.getWidth() / (float) scene.getHeight();
        float pdfWidth = 595;
        float pdfHeight = pdfWidth / aspectRatio;

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(pdfWidth, pdfHeight));
        document.addPage(page);

        WritableImage fxImage = new WritableImage((int) scene.getWidth(), (int) scene.getHeight());
        scene.snapshot(fxImage);
        BufferedImage image = SwingFXUtils.fromFXImage(fxImage, null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        PDImageXObject xImage = PDImageXObject.createFromByteArray(document, imageBytes, "ticket");
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.drawImage(xImage, 0, 0, pdfWidth, pdfHeight);
        contentStream.close();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("project_"+ project.getName());
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        Stage primaryStage = (Stage) currentNode.getScene().getWindow();
        File outputFile = fileChooser.showSaveDialog(primaryStage);

        if (outputFile != null) {
            document.save(outputFile);
            document.close();

            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.OPEN)) {
                    desktop.open(outputFile);
                }
            }
        }
    }

    public void switchToExternalDocumentation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.EXTERNAL.getView()));
            Parent root = loader.load();

            ExternalDocumentationController documentationController = loader.getController();
            documentationController.setModel(projectModel);
            documentationController.setProject(project);
            documentationController.setPane(borderPane);

            Stage window = (Stage) internalSwitch.getScene().getWindow();
            window.setTitle("External documentation window");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            PopupUtil.showAlert("Something went wrong", "Failed to switch to the external documentation", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void deleteProject(){
        Optional<ButtonType> result = PopupUtil.showConfirmationAlert("Confirm deletion", "Are you sure you want to delete this project?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Log log = new Log("Deleted project with id: "+ project.getId(), LocalDateTime.now(), "user");
                projectModel.deleteProject(project);
                projectModel.createLogEntry(log);
            } catch (Exception e) {
                PopupUtil.showAlert("Something went wrong", e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
            Stage stage = (Stage) deleteBtn.getScene().getWindow();
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
