package dk.easv.gui.controller;

import dk.easv.util.BlurEffectUtil;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingsWindowController {
    private AnchorPane anchorPane;
    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(anchorPane));
    }
}
