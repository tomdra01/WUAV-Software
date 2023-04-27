package dk.easv.bll.util;

// imports
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

// java imports
import java.util.Optional;

/**
 *
 * @author tomdra01, mrtng1
 */
public class PopupUtil {
    public static void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static Optional<ButtonType> showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        return alert.showAndWait();
    }
}
