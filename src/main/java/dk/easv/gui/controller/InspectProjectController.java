package dk.easv.gui.controller;

import dk.easv.gui.util.BlurEffectUtil;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InspectProjectController {
    private BorderPane borderPane;

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
}
