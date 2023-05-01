package dk.easv.gui.controller;

// imports
import dk.easv.gui.util.BlurEffectUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// java imports
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class NewProjectWindowController implements Initializable {
    public Button backButton;
    public Button nextButton;
    public BorderPane currentPane;
    private BorderPane borderPane;
    private List<Image> imageHistory = new ArrayList<>();
    private Stage stage;

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void previousStep() {
    }

    public void nextStep() {
    }
}
