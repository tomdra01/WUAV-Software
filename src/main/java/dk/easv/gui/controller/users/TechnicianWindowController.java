package dk.easv.gui.controller.users;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import dk.easv.be.User;
import dk.easv.gui.util.PopupUtil;
import dk.easv.be.UserSingleton;
import dk.easv.gui.util.*;
import dk.easv.gui.controller.project.ProjectStep1Controller;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.model.UserModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
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
public class TechnicianWindowController implements Initializable {
    @FXML private BorderPane mainPane;
    @FXML private HBox hbox, projectsHbox;;
    @FXML private JFXHamburger jfxHamburger;
    @FXML private JFXComboBox<String> filterComboBox;
    @FXML private JFXTextField searchBar;
    @FXML private ImageView engineerIcon;
    @FXML private JFXToggleButton toggleButton;
    private final Button createProjectButton = new Button("New project");
    private final Button logOutButton = new Button("Log out");
    private User user;
    private UserModel userModel;
    private ProjectModel projectModel;
    private ProjectDisplay projectDisplay;

    // set model
    public void setModel(UserModel userModel) {
        this.userModel= userModel;
    }

    // set users
    public void setUser(User user) {
        this.user = user;
        projectInit();
    }

    /**
     * Initializing hamburger menu.
     */
    private void hamburgerMenu(){
        VBox leftVBox = new VBox();
        leftVBox.setStyle("-fx-background-color: transparent");
        leftVBox.setPrefWidth(160);
        leftVBox.getStylesheets().add("styles/main_style.css");

        createProjectButton.setMaxWidth(200);
        logOutButton.setMaxWidth(200);

        leftVBox.getChildren().addAll(createProjectButton, logOutButton);
        VBox.setMargin(createProjectButton, new Insets(60, 20, 0, 20));
        VBox.setMargin(logOutButton, new Insets(380, 20, 0, 20));

        HamburgerUtil.showHamburger(jfxHamburger, leftVBox, mainPane);
    }

    /**
     * Actions for buttons inside hamburger menu.
     */
    private void hamburgerButtons() {
        // create new project
        createProjectButton.setOnAction(event -> {
            try {
                BlurEffectUtil.applyBlurEffect(mainPane,10);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP1.getView()));
                Parent createEventParent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Step 1");
                stage.setResizable(false);
                Scene scene = new Scene(createEventParent);
                stage.setScene(scene);
                stage.show();

                ProjectStep1Controller projectStep1 = fxmlLoader.getController();
                projectStep1.setPane(mainPane);
                projectStep1.setOnCloseRequestHandler(stage);
                projectStep1.setModel(projectModel);
                projectStep1.setUser(user);

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

    private void projectInit() {
        filterComboBox.setItems(FXCollections.observableArrayList("All", "Consumer", "Corporate & Government"));
        filterComboBox.setValue("All");

        // showcase of all projects based on the filter
        projectDisplay.showTechnicianProjects(projectsHbox, filterComboBox, searchBar, projectModel, mainPane , user);

        filterComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> projectDisplay.showTechnicianProjects(projectsHbox, filterComboBox, searchBar, projectModel, mainPane , user));

        searchBar.textProperty().addListener((observable, oldValue, newValue)
                -> projectDisplay.showTechnicianProjects(projectsHbox, filterComboBox, searchBar, projectModel, mainPane , user));
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = UserSingleton.getInstance().getUser();
        projectModel = new ProjectModel();
        projectDisplay = new ProjectDisplay();
        ClockUtil.showWidget(hbox); //clock
        hamburgerMenu(); //hamburger
        hamburgerButtons(); //buttons in hamburger

        toggleButton.setOnAction(event -> {
            if (toggleButton.isSelected()) {
                projectDisplay.setModel(projectModel);
                projectDisplay.showTableView(projectsHbox, user);
            } else {
                projectDisplay.showTechnicianProjects(projectsHbox, filterComboBox, searchBar, projectModel, mainPane, user);
            }
        });

    }
}
