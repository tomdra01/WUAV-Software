package dk.easv.gui.controller.project;

// imports
import dk.easv.be.Project;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.model.ProjectModel;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProjectFinalController implements Initializable {

    @FXML
    private Button printBtn;
    @FXML
    private Label nameLabel, locationLabel, dateLabel, customerTypeLabel;
    @FXML
    private BorderPane borderPane;
    private String projectName, businessType, projectLocation, projectDescription;
    private LocalDate projectDate;
    private byte[] projectDrawing;
    private ProjectModel projectModel;

    public void setModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public void setFields(String projectName, String businessType, String projectLocation, LocalDate projectDate, byte[] projectDrawing, String projectDescription) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;
        this.projectDrawing = projectDrawing;
        this.projectDescription = projectDescription;

        nameLabel.setText("Project name: "+projectName);
        locationLabel.setText("Location: "+projectLocation);
        dateLabel.setText("Date: "+ projectDate);
        customerTypeLabel.setText("Business type: "+businessType);
        System.out.println(projectDrawing);
    }

    public void print() throws IOException {
        Project project = new Project(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
        try {
            projectModel.createProject(project);
        } catch (SQLException e) {throw new GUIException("Failed creating a project ",e);}

        printBtn.setVisible(false);

        Scene scene = borderPane.getScene();
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

        String name = nameLabel.getText();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("project_"+ name + ".pdf");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        Stage primaryStage = (Stage) borderPane.getScene().getWindow();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
