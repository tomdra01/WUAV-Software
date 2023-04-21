package dk.easv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        URL fxmlUrl = getClass().getResource("gui/view/main_window.fxml");
        Parent root = FXMLLoader.load(fxmlUrl);

        Scene scene = new Scene(root);

        stage.setTitle("WUAV");
        stage.setScene(scene);
        stage.show();
    }
}
