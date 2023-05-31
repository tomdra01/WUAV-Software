package dk.easv.gui.controller.users;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import dk.easv.be.User;
import dk.easv.gui.util.PopupUtil;
import dk.easv.be.UserSingleton;
import dk.easv.gui.controller.AddCustomerController;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
public class SalesmanWindowController implements Initializable {
    @FXML private BorderPane mainPane;
    @FXML private HBox hbox, projectsHbox;
    @FXML private JFXHamburger jfxHamburger;
    @FXML private JFXComboBox<String> filterComboBox;
    @FXML private JFXTextField searchBar;
    @FXML private Label wuavLabel;
    @FXML private JFXToggleButton toggleButton;
    private final Button addCustomerButton = new Button("Add customer");
    private final Button sendDocument = new Button("Send document");
    private final Button logOutButton = new Button("Log out");
    private User user;
    private ProjectModel projectModel;
    private ProjectDisplay projectDisplay;

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Initializing hamburger menu.
     */
    private void hamburgerMenu(){
        VBox leftVBox = new VBox();
        leftVBox.setStyle("-fx-background-color: transparent");
        leftVBox.setPrefWidth(160);
        leftVBox.getStylesheets().add("styles/main_style.css");

        addCustomerButton.setMaxWidth(200);
        sendDocument.setMaxWidth(200);
        logOutButton.setMaxWidth(200);

        leftVBox.getChildren().addAll(addCustomerButton, sendDocument, logOutButton);
        VBox.setMargin(addCustomerButton, new Insets(60, 20, 0, 20));
        VBox.setMargin(sendDocument, new Insets(20, 20, 0, 20));
        VBox.setMargin(logOutButton, new Insets(330, 20, 0, 20));

        HamburgerUtil.showHamburger(jfxHamburger, leftVBox, mainPane);
    }

    /**
     * Actions for buttons inside hamburger menu.
     */
    private void hamburgerButtons() {
        // add customer functionality
        addCustomerButton.setOnAction(event -> {
            try {
                BlurEffectUtil.applyBlurEffect(mainPane,10);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.ADD_CUSTOMER.getView()));
                Parent parent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Add customer window");
                stage.setResizable(false);
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();

                AddCustomerController addCustomerController = fxmlLoader.getController();
                addCustomerController.setPane(mainPane);
                addCustomerController.setOnCloseRequestHandler(stage);

            } catch (IOException e) {
                PopupUtil.showAlert("Something went wrong", "Failed to open the window", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        });

        // log out functionality
        logOutButton.setOnAction(event -> {
            try {
                HamburgerUtil.setDefaultHamburger();

                Stage currentStage = (Stage) mainPane.getScene().getWindow();
                currentStage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.LOGIN.getView()));
                Parent parent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("Login");
                stage.setResizable(false);
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                PopupUtil.showAlert("Something went wrong", "Failed to logout", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        });
    }

    private void searchFilter(){
        filterComboBox.setItems(FXCollections.observableArrayList("All", "Consumer", "Corporate & Government"));
        filterComboBox.setValue("All");

        // showcase of all projects based on the filter
        projectDisplay.showSalesmanProjects(projectsHbox, filterComboBox, searchBar, projectModel, mainPane);

        filterComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> projectDisplay.showSalesmanProjects(projectsHbox, filterComboBox, searchBar, projectModel, mainPane));

        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
                projectDisplay.showSalesmanProjects(projectsHbox, filterComboBox, searchBar, projectModel, mainPane));
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = UserSingleton.getInstance().getUser();

        projectDisplay = new ProjectDisplay();
        projectModel = new ProjectModel();

        searchFilter(); // search + filter
        hamburgerMenu(); // hamburger
        hamburgerButtons(); // buttons in hamburger

        ClockUtil.showWidget(hbox); // clock
        ImageUtil.openBrowser(wuavLabel);

        toggleButton.setOnAction(event -> {
            if (toggleButton.isSelected()) {
                projectDisplay.setModel(projectModel);
                projectDisplay.showTableView(projectsHbox, user);
            } else {
                projectDisplay.showSalesmanProjects(projectsHbox, filterComboBox, searchBar, projectModel, mainPane);
            }
        });
    }
}
