package dk.easv.gui.controller;

// imports
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

// java imports
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectTemplateController implements Initializable {
    @FXML private ImageView projectImg;

    public ImageView getProjectImg() {
        return projectImg;
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
