package dk.easv.gui.util;

// imports
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;

/**
 *
 * @author tomdra01, mrtng1
 */
public class BlurEffectUtil {
    public static void applyBlurEffect(Node node, double radius) {
        node.setEffect(new GaussianBlur(radius));
    }

    public static void removeBlurEffect(Node node) {
        node.setEffect(null);
    }
}
