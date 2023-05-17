package dk.easv.gui.controller.project;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.User;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.ImagePosition;
import dk.easv.gui.util.ViewType;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// java imports
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
    private final List<List<Point2D>> linePoints = new ArrayList<>();
    private final Stack<String> actions = new Stack<>();
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
                actions.push("image");  // Add an "image" action
            } else if (event.getButton() == MouseButton.SECONDARY) {
                double x = event.getX();
                double y = event.getY();
                Point2D clickedPoint = new Point2D(x, y);
                ImagePosition nearestImage = findNearestImage(clickedPoint);
                if (nearestImage != null) {
                    if (linePoints.isEmpty() || linePoints.get(linePoints.size() - 1).size() == 2) {
                        linePoints.add(new ArrayList<>());
                    }
                    linePoints.get(linePoints.size() - 1).add(new Point2D(nearestImage.getX() + nearestImage.getWidth() / 2, nearestImage.getY() + nearestImage.getHeight() / 2));
                    connectImagesWithLines(gc);
                    actions.push("line");  // Add a "line" action
                }
            }
        });

        canvas.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.MIDDLE) {
                double x = event.getX();
                double y = event.getY();
                Point2D clickedPoint = new Point2D(x, y);
                List<Point2D> nearestLine = findNearestLine(clickedPoint);
                if (nearestLine != null) {
                    linePoints.remove(nearestLine);
                    connectImagesWithLines(canvas.getGraphicsContext2D());
                }
            }
        });

        stepBackBtn.setOnAction(event -> stepBack(gc));
    }

    private ImagePosition findNearestImage(Point2D point) {
        ImagePosition nearestImage = null;
        double minDistance = Double.MAX_VALUE;
        for (ImagePosition imagePosition : imageHistory) {
            double centerX = imagePosition.getX() + imagePosition.getWidth() / 2;
            double centerY = imagePosition.getY() + imagePosition.getHeight() / 2;
            double distance = point.distance(centerX, centerY);
            if (distance < minDistance) {
                minDistance = distance;
                nearestImage = imagePosition;
            }
        }
        return nearestImage;
    }

    private List<Point2D> findNearestLine(Point2D point) {
        List<Point2D> nearestLine = null;
        double minDistance = Double.MAX_VALUE;
        for (List<Point2D> line : linePoints) {
            if (line.size() == 2) {
                Point2D startPoint = line.get(0);
                Point2D endPoint = line.get(1);
                double distance = calculatePointToLineDistance(startPoint, endPoint, point);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestLine = line;
                }
            }
        }
        return minDistance < 10 ? nearestLine : null;  // Only return the line if the distance is less than 10
    }

    private double calculatePointToLineDistance(Point2D startPoint, Point2D endPoint, Point2D point) {
        double dx = endPoint.getX() - startPoint.getX();
        double dy = endPoint.getY() - startPoint.getY();
        double lineLengthSquared = dx * dx + dy * dy;
        if (lineLengthSquared == 0) return point.distance(startPoint);
        double t = ((point.getX() - startPoint.getX()) * dx + (point.getY() - startPoint.getY()) * dy) / lineLengthSquared;
        t = Math.max(0, Math.min(1, t));
        return point.distance(startPoint.getX() + t * dx, startPoint.getY() + t * dy);
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

        for (List<Point2D> line : linePoints) {
            if (line.size() == 2) {
                Point2D startPoint = line.get(0);
                Point2D endPoint = line.get(1);
                gc.strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
            }
        }
    }

    private void stepBack(GraphicsContext gc) {
        if (!actions.isEmpty()) {
            String lastAction = actions.pop();
            if (lastAction.equals("image")) {
                if (!imageHistory.isEmpty()) {
                    imageHistory.pop();
                    imagePositions.remove(imagePositions.size() - 1);
                }
            } else if (lastAction.equals("line")) {
                if (!linePoints.isEmpty()) {
                    linePoints.remove(linePoints.size() - 1);
                }
            }
        }

        // Redraw the canvas and lines
        connectImagesWithLines(gc);
    }

    public void nextStep() {
        try {
            // Create a snapshot of the scene
            Scene canvasScene = canvas.getScene();
            WritableImage fxImage = new WritableImage((int) canvasScene.getWidth(), (int) canvasScene.getHeight());
            canvasScene.snapshot(fxImage);
            BufferedImage image = SwingFXUtils.fromFXImage(fxImage, null);

            // Convert the snapshot to a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", outputStream);
            this.projectDrawing = outputStream.toByteArray();

            // Continue to the next step
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
            throw new RuntimeException("Failed to change the window", e);
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
            throw new RuntimeException("Failed to change the window", e);
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
