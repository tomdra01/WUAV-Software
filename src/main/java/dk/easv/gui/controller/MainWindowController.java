package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import dk.easv.util.BlurEffectUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

// java imports
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class MainWindowController implements Initializable {
    @FXML
    private JFXHamburger jfxHamburger;
    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane anchorPane;
    private boolean isVBoxAdded;

    public void newProject() throws IOException {
        BlurEffectUtil.applyBlurEffect(anchorPane,10);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/new_project_window.fxml"));
        Parent createEventParent = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New Project");
        stage.setResizable(false);
        Scene scene = new Scene(createEventParent);
        stage.setScene(scene);

        NewProjectWindowController newProjectWindowController = fxmlLoader.getController();
        newProjectWindowController.setAnchorPane(anchorPane);
        newProjectWindowController.setOnCloseRequestHandler(stage);

        stage.show();
    }

    public void showCustomers() throws IOException {
        BlurEffectUtil.applyBlurEffect(anchorPane,10);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/customers_window.fxml"));
        Parent createEventParent = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Customers");
        stage.setResizable(false);
        Scene scene = new Scene(createEventParent);
        stage.setScene(scene);

        CustomersWindowController customersWindowController = fxmlLoader.getController();
        customersWindowController.setAnchorPane(anchorPane);
        customersWindowController.setOnCloseRequestHandler(stage);

        stage.show();
    }

    public void showReports() throws IOException {
        BlurEffectUtil.applyBlurEffect(anchorPane,10);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/reports_window.fxml"));
        Parent createEventParent = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Reports");
        stage.setResizable(false);
        Scene scene = new Scene(createEventParent);
        stage.setScene(scene);

        ReportsWindowController reportsWindowController = fxmlLoader.getController();
        reportsWindowController.setAnchorPane(anchorPane);
        reportsWindowController.setOnCloseRequestHandler(stage);

        stage.show();
    }

    public void openSettings() throws IOException {
        BlurEffectUtil.applyBlurEffect(anchorPane,10);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/settings_window.fxml"));
        Parent createEventParent = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Settings");
        stage.setResizable(false);
        Scene scene = new Scene(createEventParent);
        stage.setScene(scene);

        SettingsWindowController settingsWindowController = fxmlLoader.getController();
        settingsWindowController.setAnchorPane(anchorPane);
        settingsWindowController.setOnCloseRequestHandler(stage);

        stage.show();
    }

    public void logout() {
        try {
            Stage currentStage = (Stage) anchorPane.getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/login_window.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Login");
            stage.setResizable(false);
            Scene scene = new Scene(parent);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hamburgerMenu(){
        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(jfxHamburger);
        transition.setRate(-1);
        jfxHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();


            VBox leftVBox = new VBox();
            Button createProjectButton = new Button("New project");
            Button logOutButton = new Button("Log out");

            leftVBox.setPrefWidth(170);
            leftVBox.getChildren().addAll(createProjectButton, logOutButton);
            VBox.setMargin(createProjectButton, new Insets(20, 20, 20, 20));
            VBox.setMargin(logOutButton, new Insets(480, 20, 20, 20));

            if (isVBoxAdded) {
                mainPane.setLeft(null);
            } else {
                mainPane.setLeft(leftVBox);
            }
            isVBoxAdded = !isVBoxAdded;
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburgerMenu();
    }
}
