package dk.easv.gui.util;

// imports
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
}
