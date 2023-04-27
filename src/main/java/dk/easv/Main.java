package dk.easv;

// imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// java imports
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 *
 * @author tomdra01, mrtng1
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        URL fxmlUrl = getClass().getResource("/views/admin_window.fxml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(fxmlUrl));

        Scene scene = new Scene(root);
        stage.setTitle("WUAV");
        stage.setScene(scene);
        stage.show();
    }
}
