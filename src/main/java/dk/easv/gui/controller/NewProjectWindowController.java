package dk.easv.gui.controller;

// imports
import dk.easv.gui.util.BlurEffectUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class NewProjectWindowController implements Initializable {
    @FXML
    private Canvas drawOnCanvas;
    @FXML
    private Button projectorButton, screenButton, tabletButton, speakersButton;
    private Image image;
    private BorderPane borderPane;
    private List<Image> imageHistory = new ArrayList<>();

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    /**
     * Enables user to select from device and draw it on canvas
     */
    private void drawCanvas(){
        GraphicsContext gc = drawOnCanvas.getGraphicsContext2D();

        projectorButton.setOnAction(event -> { image = new Image("/images/icons/projector.png");});
        screenButton.setOnAction(event -> { image = new Image("/images/icons/screen.png");});
        tabletButton.setOnAction(event -> { image = new Image("/images/icons/tablet.png");});
        speakersButton.setOnAction(event -> { image = new Image("/images/icons/speakers.png");});

        drawOnCanvas.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                double x = event.getX();
                double y = event.getY();
                double width = 25;
                double height = 25;
                gc.drawImage(image, x, y, width, height);
                imageHistory.add(new Image(image.getUrl(), width, height, true, true));
            }
        });
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawCanvas();
    }
}
