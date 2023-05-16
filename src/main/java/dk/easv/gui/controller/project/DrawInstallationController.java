package dk.easv.gui.controller.project;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.User;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.ImagePosition;
import dk.easv.gui.util.ViewType;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// java imports
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

/**
 *
 * @author tomdra01, mrtng1
 */
public class DrawInstallationController implements Initializable {
    @FXML private BorderPane currentPane;
    @FXML private ScrollPane scrollPane;
    @FXML private Canvas canvas;
    @FXML private Button projectorButton, screenButton, tabletButton, speakersButton, nextStepBtn, previousStepBtn, stepBackBtn;
    private String projectName, businessType, projectLocation, projectDescription;
    private LocalDate projectDate;
    private byte[] projectDrawing;
    private byte[] projectPhoto1, projectPhoto2, projectPhoto3;
    private Image img1, img2, img3;
    private Image image;
    private final Stack<ImagePosition> imageHistory = new Stack<>();
    private ProjectModel projectModel;
    private HBox projectHbox;
    private JFXComboBox<String> filterComboBox;
    private JFXTextField searchBar;
    private BorderPane mainPane;
    private User user;
    private final List<Point2D> imagePositions = new ArrayList<>();
    SimpleBooleanProperty isDragOperation = new SimpleBooleanProperty(false);

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

    private void drawCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        projectorButton.setOnAction(event -> {image = new Image("/images/icons/projector.png");});
        screenButton.setOnAction(event -> {image = new Image("/images/icons/screen.png");});
        tabletButton.setOnAction(event -> {image = new Image("/images/icons/tablet.png");});
        speakersButton.setOnAction(event -> {image = new Image("/images/icons/speakers.png");});

        canvas.setOnMousePressed(event -> {
            isDragOperation.set(false);
        });

        canvas.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                isDragOperation.set(true);
                if (!imageHistory.isEmpty()) {
                    Point2D mousePosition = new Point2D(event.getX(), event.getY());

                    for (ImagePosition imagePosition : imageHistory) {
                        // If the mouse is within the bounds of the image
                        if (mousePosition.getX() >= imagePosition.getX() && mousePosition.getX() <= imagePosition.getX() + imagePosition.getWidth()
                                && mousePosition.getY() >= imagePosition.getY() && mousePosition.getY() <= imagePosition.getY() + imagePosition.getHeight()) {
                            // Update the image's position to the current mouse position
                            imagePosition.setX(mousePosition.getX() - imagePosition.getWidth() / 2);
                            imagePosition.setY(mousePosition.getY() - imagePosition.getHeight() / 2);
                        }
                    }

                    // Redraw the canvas and lines
                    connectImagesWithLines(gc);
                }
            }
        });

        canvas.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY && !isDragOperation.get()) {
                canvas.requestFocus();
                double x = event.getX();
                double y = event.getY();
                double width = 25;
                double height = 25;
                gc.drawImage(image, x, y, width, height);

                imageHistory.push(new ImagePosition(image, x, y, width, height));

                imagePositions.add(new Point2D(x + width / 2, y + height / 2));

                // Connect the images with lines
                connectImagesWithLines(gc);
            }
        });

        stepBackBtn.setOnAction(event -> stepBack(gc));
    }

    private void connectImagesWithLines(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);

        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw lines
        for (ImagePosition imagePosition : imageHistory) {
            Image image = imagePosition.getImage();
            double x = imagePosition.getX();
            double y = imagePosition.getY();
            double width = imagePosition.getWidth();
            double height = imagePosition.getHeight();
            gc.drawImage(image, x, y, width, height);
        }

        if (imageHistory.size() > 1) {
            Point2D startPoint = new Point2D(imageHistory.get(0).getX() + imageHistory.get(0).getWidth() / 2, imageHistory.get(0).getY() + imageHistory.get(0).getHeight() / 2);

            for (int i = 1; i < imageHistory.size(); i++) {
                Point2D endPoint = new Point2D(imageHistory.get(i).getX() + imageHistory.get(i).getWidth() / 2, imageHistory.get(i).getY() + imageHistory.get(i).getHeight() / 2);
                gc.strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
                startPoint = endPoint;
            }
        }
    }

    private void stepBack(GraphicsContext gc) {
        if (!imageHistory.isEmpty()) {
            imageHistory.pop();
            imagePositions.remove(imagePositions.size() - 1);

            // Redraw the canvas and lines
            connectImagesWithLines(gc);
        }
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
