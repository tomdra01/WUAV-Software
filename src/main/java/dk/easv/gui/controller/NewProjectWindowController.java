package dk.easv.gui.controller;

// imports
import dk.easv.bll.util.BlurEffectUtil;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author tomdra01, mrtng1
 */
public class NewProjectWindowController {
    private BorderPane borderPane;

    public void setAnchorPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }
}
