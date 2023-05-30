package dk.easv.gui.util;

// imports
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

// java imports
import java.util.Objects;
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

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(PopupUtil.class.getResource("/styles/alert_style.css")).toExternalForm());
        dialogPane.getStyleClass().add("custom-dialog-pane");
        alert.showAndWait();
    }

    public static Optional<ButtonType> showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(PopupUtil.class.getResource("/styles/alert_style.css")).toExternalForm());
        dialogPane.getStyleClass().add("custom-dialog-pane");
        return alert.showAndWait();
    }
}
