package dk.easv.gui.util;

import dk.easv.bll.exception.GUIException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Objects;

public class LoaderUtil {

    public static void loadFXML(String path, Button button, String title, Scene scene){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(LoaderUtil.class.getResource(path)));
            Stage window = (Stage) button.getScene().getWindow();
            window.setTitle("Step 3");
            scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to change the window", e);
        }
    }
}
