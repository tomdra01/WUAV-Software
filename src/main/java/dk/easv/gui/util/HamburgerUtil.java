package dk.easv.gui.util;

// imports
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author tomdra01, mrtng1
 */
public class HamburgerUtil {
    private static boolean isMenuOpen;

    public static void showHamburger(JFXHamburger hamburgerButton, VBox menuItems, BorderPane borderPane){
        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburgerButton);
        transition.setRate(-1);

        hamburgerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (isMenuOpen) {
                borderPane.setLeft(null);
            } else {
                borderPane.setLeft(menuItems);
            }
            isMenuOpen = !isMenuOpen;
        });
    }
}
