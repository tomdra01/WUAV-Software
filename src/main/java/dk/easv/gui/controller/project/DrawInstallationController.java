package dk.easv.gui.controller.project;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.User;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.ViewType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
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
public class DrawInstallationController implements Initializable {
    @FXML private ScrollPane scrollPane;
    @FXML private Canvas canvas;
    @FXML private Button projectorButton, screenButton, tabletButton, speakersButton, nextStepBtn, previousStepBtn;
    private String projectName, businessType, projectLocation, projectDescription;
    private LocalDate projectDate;
    private byte[] projectDrawing;
    private byte[] projectPhoto1, projectPhoto2, projectPhoto3;
    private Image img1, img2, img3;
    private Image image;
    private final ObservableList<Image> imageHistory = FXCollections.observableArrayList();
    private ProjectModel projectModel;
    private HBox projectHbox;
    private JFXComboBox<String> filterComboBox;
    private JFXTextField searchBar;
    private BorderPane mainPane;
    private User user;

    public void setMainPage(HBox projectHbox, JFXComboBox<String> filterComboBox, JFXTextField searchBar, BorderPane borderPane){
        this.projectHbox=projectHbox;
        this.filterComboBox=filterComboBox;
        this.searchBar = searchBar;
        this.mainPane = borderPane;
    }

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

    public void nextStep() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP4.getView()));
            Parent root = loader.load();

            ProjectStep4Controller projectStep4 = loader.getController();
            projectStep4.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            projectStep4.setImages(img1, img2, img3, projectPhoto1, projectPhoto2, projectPhoto3);
            projectStep4.setModel(projectModel);
            projectStep4.setUser(user);
            projectStep4.setMainPage(projectHbox, filterComboBox, searchBar, mainPane);

            Stage window = (Stage) nextStepBtn.getScene().getWindow();
            window.setTitle("Step 4");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to change the window", e);
        }
    }

    public void previousStep() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP3.getView()));
            Parent root = loader.load();

            ProjectStep3Controller projectStep3 = loader.getController();
            projectStep3.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            projectStep3.setImages(img1, img2, img3, projectPhoto1, projectPhoto2, projectPhoto3);
            projectStep3.setModel(projectModel);
            projectStep3.setUser(user);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Step 3");
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
        drawCanvas();
    }
}
