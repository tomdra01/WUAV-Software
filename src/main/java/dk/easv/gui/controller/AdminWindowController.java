package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXHamburger;
import dk.easv.gui.util.ClockUtil;
import dk.easv.gui.util.HamburgerUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

// java imports
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class AdminWindowController implements Initializable {
    @FXML
    private HBox hbox;
    @FXML
    private JFXHamburger jfxHamburger;
    @FXML
    private BorderPane mainPane;
    private final Button createUser = new Button("Create user");
    private final Button logOutButton = new Button("Log out");
    private final Button showTechnicians = new Button("Show technicians");
    private final Button showCustomers = new Button("Show customers");
    private final Button showLog = new Button("See log");

    /**
     * Initializing hamburger menu.
     */
    private void hamburgerMenu(){
        VBox leftVBox = new VBox();
        leftVBox.setPrefWidth(160);
        leftVBox.getStylesheets().add("styles/main_style.css");

        createUser.setMaxWidth(200);
        showTechnicians.setMaxWidth(200);
        showCustomers.setMaxWidth(200);
        showLog.setMaxWidth(200);
        logOutButton.setMaxWidth(200);

        leftVBox.getChildren().addAll(createUser, showTechnicians, showCustomers, showLog, logOutButton);
        VBox.setMargin(createUser, new Insets(60, 20, 0, 20));
        VBox.setMargin(showTechnicians, new Insets(20, 20, 0, 20));
        VBox.setMargin(showCustomers, new Insets(20, 20, 0, 20));
        VBox.setMargin(showLog, new Insets(20, 20, 0, 20));
        VBox.setMargin(logOutButton, new Insets(240, 20, 0, 20));

        HamburgerUtil.showHamburger(jfxHamburger, leftVBox, mainPane);
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburgerMenu(); //hamburger
        ClockUtil.showWidget(hbox); //clock
    }
}