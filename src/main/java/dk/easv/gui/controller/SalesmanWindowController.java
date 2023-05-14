package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.User;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.model.UserModel;
import dk.easv.gui.util.ClockUtil;
import dk.easv.gui.util.HamburgerUtil;
import dk.easv.gui.util.ProjectDisplay;
import dk.easv.gui.util.ViewType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    @FXML private JFXComboBox<String> filterComboBox;
    @FXML private JFXTextField searchBar;
    @FXML private HBox projectsHbox;
    @FXML
    private BorderPane mainPane;
    @FXML private HBox hbox;
    @FXML private JFXHamburger jfxHamburger;
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

        logOutButton.setMaxWidth(200);

        leftVBox.getChildren().addAll(logOutButton);
        VBox.setMargin(logOutButton, new Insets(440, 20, 0, 20));

        HamburgerUtil.showHamburger(jfxHamburger, leftVBox, mainPane);
    }

    /**
     * Actions for buttons inside hamburger menu.
     */
    private void hamburgerButtons() {
        // log out functionality
        logOutButton.setOnAction(event -> {
            HamburgerUtil.setDefaultHamburger();
            try {
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
                throw new GUIException("Failed to logout", e);
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
        projectDisplay = new ProjectDisplay();
        projectModel = new ProjectModel();
        searchFilter(); // search + filter
        hamburgerMenu(); // hamburger
        hamburgerButtons(); // buttons in hamburger
        ClockUtil.showWidget(hbox); // clock
    }
}
