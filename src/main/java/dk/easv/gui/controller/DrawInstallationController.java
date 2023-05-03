package dk.easv.gui.controller;

// imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;

// java imports
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class DrawInstallationController implements Initializable {
    @FXML private ScrollPane scrollPane;
    @FXML private Canvas canvas;
    @FXML private Button projectorButton, screenButton, tabletButton, speakersButton, nextStepBtn, previousStepBtn;
    private String projectName, businessType, projectLocation;
    private LocalDate projectDate;
    private Image image;
    private final ObservableList<Image> imageHistory = FXCollections.observableArrayList();;

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

    /**
     * Enables user to select from device and draw it on canvas
     */
    private void drawCanvas(){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        projectorButton.setOnAction(event -> { image = new Image("/images/icons/projector.png");});
        screenButton.setOnAction(event -> { image = new Image("/images/icons/screen.png");});
        tabletButton.setOnAction(event -> { image = new Image("/images/icons/tablet.png");});
        speakersButton.setOnAction(event -> { image = new Image("/images/icons/speakers.png");});

        canvas.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                canvas.requestFocus(); // Request focus on the canvas to remove focus from the scroll pane
                double x = event.getX();
                double y = event.getY();
                double width = 25;
                double height = 25;
                gc.drawImage(image, x, y, width, height);
                imageHistory.add(new Image(image.getUrl(), width, height, true, true));
            }
        });
    }

    public void previousStep() {
    }

    public void nextStep() {
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawCanvas();
    }
}
