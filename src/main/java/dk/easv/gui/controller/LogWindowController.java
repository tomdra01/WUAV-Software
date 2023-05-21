package dk.easv.gui.controller;

import dk.easv.gui.util.BlurEffectUtil;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LogWindowController {
    private BorderPane borderPane;
    public void setPane(BorderPane mainPane) {
        this.borderPane = mainPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }
}
