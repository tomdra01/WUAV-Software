package dk.easv.gui.util;

// imports
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ImageUtil {
    public static void iconAnimation(ImageView engineerIcon) {
        Image technicianRGB = new Image("/images/engineer_icon_rgb.jpeg");
        Image technicianBW = new Image("/images/engineer_icon_bw.jpeg");

        engineerIcon.setOnMouseEntered(event -> engineerIcon.setImage(technicianRGB));
        engineerIcon.setOnMouseExited(event -> engineerIcon.setImage(technicianBW));
    }

    public static void openBrowser(Label label) {
        label.setOnMouseClicked(event -> {
            System.out.println("hello");
            openWebPage();
        });
    }

    private static void openWebPage() {
        Platform.runLater(() -> {
            try {
                String os = System.getProperty("os.name").toLowerCase();
                if (os.contains("win")) {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "https://www.wuav.dk/");
                } else if (os.contains("mac")) {
                    Runtime.getRuntime().exec("open " + "https://www.wuav.dk/");
                } else if (os.contains("nix") || os.contains("nux")) {
                    Runtime.getRuntime().exec("xdg-open " + "https://www.wuav.dk/");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
