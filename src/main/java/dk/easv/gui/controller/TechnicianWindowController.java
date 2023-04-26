package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import dk.easv.bll.util.BlurEffectUtil;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

// java imports
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class TechnicianWindowController implements Initializable {
    @FXML
    private HBox hbox;
    @FXML
    private JFXHamburger jfxHamburger;
    @FXML
    private BorderPane mainPane;
    private final Button createProjectButton = new Button("New project");
    private final Button logOutButton = new Button("Log out");
    private boolean isVBoxAdded;
    private Tile clockTile;

    /**
     * Initializing hamburger menu.
     */
    private void hamburgerMenu(){
        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(jfxHamburger);
        transition.setRate(-1);
        jfxHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            VBox leftVBox = new VBox();
            leftVBox.setStyle("-fx-background-color: transparent");
            leftVBox.setPrefWidth(160);
            leftVBox.getStylesheets().add("styles/main_style.css");

            leftVBox.getChildren().addAll(createProjectButton, logOutButton);
            VBox.setMargin(createProjectButton, new Insets(60, 20, 0, 50));
            VBox.setMargin(logOutButton, new Insets(380, 20, 0, 70));

            if (isVBoxAdded) {
                mainPane.setLeft(null);
            } else {
                mainPane.setLeft(leftVBox);
            }
            isVBoxAdded = !isVBoxAdded;
        });
    }

    /**
     * Actions for buttons inside hamburger menu.
     */
    private void hamburgerButtons() {
        createProjectButton.setOnAction(event -> {
            BlurEffectUtil.applyBlurEffect(mainPane,10);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/new_project_window.fxml"));
                Parent createEventParent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("New Project");
                stage.setResizable(false);
                Scene scene = new Scene(createEventParent);
                stage.setScene(scene);
                stage.show();

                NewProjectWindowController newProjectWindowController = fxmlLoader.getController();
                newProjectWindowController.setAnchorPane(mainPane);
                newProjectWindowController.setOnCloseRequestHandler(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logOutButton.setOnAction(event -> {
            try {
                Stage currentStage = (Stage) mainPane.getScene().getWindow();
                currentStage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/login_window.fxml"));
                Parent parent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("Login");
                stage.setResizable(false);
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Setting clock widget.
     */
    private void setClockTile(){
        clockTile = TileBuilder.create()
                .skinType(Tile.SkinType.CLOCK)
                .dateVisible(true)
                .locale(Locale.UK)
                .running(true)
                .build();

        hbox.getChildren().addAll(clockTile);
        HBox.setMargin(clockTile, new Insets(-5, 0, 0, 1000));
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setClockTile(); //clock
        hamburgerMenu(); //hamburger
        hamburgerButtons(); //buttons in hamburger
    }
}
