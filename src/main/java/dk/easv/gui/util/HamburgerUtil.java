package dk.easv.gui.util;

// imports
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author tomdra01, mrtng1
 */
public class HamburgerUtil {
    private static boolean isMenuOpen;
    private static HamburgerSlideCloseTransition transition;

    public static void showHamburger(JFXHamburger hamburgerButton, VBox menuItems, BorderPane borderPane) {
        transition = new HamburgerSlideCloseTransition(hamburgerButton);
        transition.setRate(-1);

        TranslateTransition openNav = new TranslateTransition(new Duration(200), menuItems);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(200), menuItems);

        hamburgerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (isMenuOpen) {
                closeNav.setToX(-(menuItems.getWidth()));
                closeNav.play();
                borderPane.setLeft(null);
            } else {
                borderPane.setLeft(menuItems);
                BorderPane.setMargin(menuItems, new Insets(0, 0, 0, 20));
                openNav.play();
            }
            isMenuOpen = !isMenuOpen;
        });
    }

    public static void setDefaultHamburger() {
        isMenuOpen = false;
        transition.setRate(-1);
        transition.play();
    }
}
