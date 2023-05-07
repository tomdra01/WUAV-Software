package dk.easv.gui.controller;

// imports
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    @FXML private Label nameLabel, dateLabel, locationLabel, textLabel;

    public ImageView getProjectImg() {
        return projectImg;
    }
    public Label getNameLabel() {return nameLabel;}
    public Label getLocationLabel() {return locationLabel;}
    public Label getDateLabel() {return dateLabel;}
    public Label getTextLabel() {return textLabel;}

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
