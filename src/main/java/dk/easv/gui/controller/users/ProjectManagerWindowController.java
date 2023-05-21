package dk.easv.gui.controller.users;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.User;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.controller.AssignNewTechnicianController;
import dk.easv.gui.controller.SelectProjectController;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.model.UserModel;
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
import javafx.scene.image.ImageView;
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
public class ProjectManagerWindowController implements Initializable {
    @FXML private BorderPane mainPane;
    @FXML private HBox hbox, projectsHbox;
    @FXML private JFXHamburger jfxHamburger;
    @FXML private JFXComboBox<String> filterComboBox;
    @FXML private JFXTextField searchBar;
    @FXML private ImageView engineerIcon;
    private final Button editButton = new Button("Edit project");
    private final Button logOutButton = new Button("Log out");
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
        leftVBox.setStyle("-fx-background-color: transparent");
        leftVBox.setPrefWidth(160);
        leftVBox.getStylesheets().add("styles/main_style.css");

        editButton.setMaxWidth(200);
        logOutButton.setMaxWidth(200);

        leftVBox.getChildren().addAll(editButton, logOutButton);
        VBox.setMargin(editButton, new Insets(60, 20, 0, 20));
        VBox.setMargin(logOutButton, new Insets(380, 20, 0, 20));

        HamburgerUtil.showHamburger(jfxHamburger, leftVBox, mainPane);
    }

    /**
     * Actions for buttons inside hamburger menu.
     */
    private void hamburgerButtons() {
        // edit functionality
        editButton.setOnAction(event -> {
            try {
                BlurEffectUtil.applyBlurEffect(mainPane,10);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.SELECT_PROJECT.getView()));
                Parent createEventParent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Select project window");
                stage.setResizable(false);
                Scene scene = new Scene(createEventParent);
                stage.setScene(scene);

                SelectProjectController selectProjectController = fxmlLoader.getController();
                selectProjectController.setUser(user);
                selectProjectController.setProjectModel(projectModel);
                selectProjectController.setPane(mainPane);
                selectProjectController.setOnCloseRequestHandler(stage);
                stage.show();
            } catch (IOException e) {
                PopupUtil.showAlert("Something went wrong", "Failed to open the window", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        });

        // assign new technician
        engineerIcon.setOnMouseClicked(event -> {
            try {
                BlurEffectUtil.applyBlurEffect(mainPane,10);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.ASSIGN_NEW_TECHNICIAN.getView()));
                Parent createEventParent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Assign technician window");
                stage.setResizable(false);
                Scene scene = new Scene(createEventParent);
                stage.setScene(scene);

                AssignNewTechnicianController assignNewTechnicianController = fxmlLoader.getController();
                assignNewTechnicianController.setModel(userModel, projectModel);
                assignNewTechnicianController.setUser(user);
                assignNewTechnicianController.setPane(mainPane);
                assignNewTechnicianController.setOnCloseRequestHandler(stage);
                stage.show();
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

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectDisplay = new ProjectDisplay();
        projectModel = new ProjectModel();
        ClockUtil.showWidget(hbox);//clock
        hamburgerMenu(); //hamburger
        hamburgerButtons(); //buttons in hamburger
        searchFilter();
        ImageUtil.iconAnimation(engineerIcon);
    }
}
