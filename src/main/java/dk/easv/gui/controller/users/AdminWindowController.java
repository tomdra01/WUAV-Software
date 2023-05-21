package dk.easv.gui.controller.users;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.User;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.controller.CreateUserController;
import dk.easv.gui.controller.LogWindowController;
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
import javafx.scene.image.Image;
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
public class AdminWindowController implements Initializable {
    @FXML private BorderPane mainPane;
    @FXML private HBox hbox, projectsHbox;
    @FXML private JFXHamburger jfxHamburger;
    @FXML private JFXComboBox<String> filterComboBox;
    @FXML private JFXTextField searchBar;
    @FXML private ImageView engineerIcon;
    private  final  Button createProjectButton = new Button("New project");
    private final Button createUserButton = new Button("Create user");
    private final Button logOutButton = new Button("Log out");
    private final Button showTechniciansButton = new Button("Show technicians");
    private final Button showCustomersButton = new Button("Show customers");
    private final Button showLogButton = new Button("See log");
    private User user;
    private UserModel userModel;
    private ProjectModel projectModel;
    private ProjectDisplay projectDisplay;

    public void setModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Initializing hamburger menu.
     */
    private void hamburgerMenu(){
        VBox leftVBox = new VBox();
        leftVBox.setPrefWidth(160);
        leftVBox.getStylesheets().add("styles/main_style.css");

        createProjectButton.setMaxWidth(200);
        createUserButton.setMaxWidth(200);
        showTechniciansButton.setMaxWidth(200);
        showCustomersButton.setMaxWidth(200);
        showLogButton.setMaxWidth(200);
        logOutButton.setMaxWidth(200);

        leftVBox.getChildren().addAll(createProjectButton, createUserButton, showTechniciansButton, showCustomersButton, showLogButton, logOutButton);
        VBox.setMargin(createProjectButton, new Insets(60, 20, 0, 20));
        VBox.setMargin(createUserButton, new Insets(20, 20, 0, 20));
        VBox.setMargin(showTechniciansButton, new Insets(20, 20, 0, 20));
        VBox.setMargin(showCustomersButton, new Insets(20, 20, 0, 20));
        VBox.setMargin(showLogButton, new Insets(20, 20, 0, 20));
        VBox.setMargin(logOutButton, new Insets(190, 20, 0, 20));

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
                Parent parent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Step 1 window");
                stage.setResizable(false);
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();

                ProjectStep1Controller projectStep1 = fxmlLoader.getController();
                projectStep1.setPane(mainPane);
                projectStep1.setOnCloseRequestHandler(stage);
                projectStep1.setModel(projectModel);
                projectStep1.setUser(user);
                projectStep1.setMainPage(projectsHbox, filterComboBox, searchBar, mainPane);

            } catch (IOException e) {
                PopupUtil.showAlert("Something went wrong", "Failed to open the window", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        });

        // create users window
        createUserButton.setOnAction(event -> {
            try {
                BlurEffectUtil.applyBlurEffect(mainPane,10);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.CREATE_USER.getView()));
                Parent createEventParent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Create user window");
                stage.setResizable(false);
                Scene scene = new Scene(createEventParent);
                stage.setScene(scene);

                CreateUserController createUserController = fxmlLoader.getController();
                createUserController.setModel(userModel);
                createUserController.setPane(mainPane);
                createUserController.setOnCloseRequestHandler(stage);
                stage.show();
            } catch (IOException e) {
                PopupUtil.showAlert("Something went wrong", "Failed to open the window", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        });

        // technician listView
        showTechniciansButton.setOnAction(event -> {

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
                stage.setTitle("Login window");
                stage.setResizable(false);
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                PopupUtil.showAlert("Something went wrong", "Failed to logout", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        });

        showLogButton.setOnAction(event -> {
            try {
                BlurEffectUtil.applyBlurEffect(mainPane,10);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.LOG.getView()));
                Parent createEventParent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Log");
                stage.setResizable(false);
                Scene scene = new Scene(createEventParent);
                stage.setScene(scene);

                LogWindowController logWindowController = fxmlLoader.getController();
                //createUserController.setModel(userModel);
                logWindowController.setPane(mainPane);
                logWindowController.setOnCloseRequestHandler(stage);
                stage.show();
            } catch (IOException e) {
                PopupUtil.showAlert("Something went wrong", "Failed to open the window", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        });
    }

    private void searchFilter(){
        filterComboBox.setItems(FXCollections.observableArrayList("All", "Consumer", "Corporate & Government"));
        filterComboBox.setValue("All");

        // showcase of all projects based on the filter
        projectDisplay.showAllProjects(projectsHbox, filterComboBox, searchBar, projectModel, mainPane);

        filterComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> projectDisplay.showAllProjects(projectsHbox, filterComboBox, searchBar, projectModel, mainPane));

        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
                projectDisplay.showAllProjects(projectsHbox, filterComboBox, searchBar, projectModel, mainPane));
    }

    private void changingImage() {
        Image technicianRGB = new Image("/images/engineer_icon_rgb.jpeg");
        Image technicianBW = new Image("/images/engineer_icon_bw.jpeg");

        engineerIcon.setOnMouseEntered(event -> engineerIcon.setImage(technicianRGB));
        engineerIcon.setOnMouseExited(event -> engineerIcon.setImage(technicianBW));
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectDisplay = new ProjectDisplay();
        projectModel = new ProjectModel();
        searchFilter(); // search + filter
        hamburgerMenu(); // hamburger
        hamburgerButtons(); // buttons in hamburger
        ClockUtil.showWidget(hbox); // clock
        ImageUtil.iconAnimation(engineerIcon);
    }
}