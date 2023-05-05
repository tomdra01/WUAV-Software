package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXHamburger;
import dk.easv.be.Project;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.controller.project.ProjectInfoController;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.model.UserModel;
import dk.easv.gui.util.BlurEffectUtil;
import dk.easv.gui.util.ClockUtil;
import dk.easv.gui.util.HamburgerUtil;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

// java imports
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class TechnicianWindowController implements Initializable {
    @FXML private HBox projectsHbox;
    @FXML private ScrollPane scrollPane;
    @FXML
    private HBox hbox;
    @FXML
    private JFXHamburger jfxHamburger;
    @FXML
    private BorderPane mainPane;
    private UserModel userModel;
    private ProjectModel projectModel;
    private final Button createProjectButton = new Button("New project");
    private final Button logOutButton = new Button("Log out");

    public void setModel(UserModel userModel) {
        this.userModel= userModel;
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
            BlurEffectUtil.applyBlurEffect(mainPane,10);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP1.getView()));
                Parent createEventParent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Step 1");
                stage.setResizable(false);
                Scene scene = new Scene(createEventParent);
                stage.setScene(scene);
                stage.show();

                ProjectInfoController projectInfoController = fxmlLoader.getController();
                projectInfoController.setPane(mainPane);
                projectInfoController.setOnCloseRequestHandler(stage);
                projectInfoController.setModel(projectModel);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // log out functionality
        logOutButton.setOnAction(event -> {
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

    private void showProjects(){
        List<Project> projects = projectModel.getProjects();
        int limit = 15;
        int counter = 0;

        for (Project project : projects) {
            if (counter == limit) {
                break;
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_CARD.getView()));
                AnchorPane root = loader.load();

                projectsHbox.getChildren().add(root);
                projectsHbox.setMargin(root, new Insets(0, 5, 0, 5));
            } catch (IOException e) {
                e.printStackTrace();
            }
            counter++;
        }
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectModel = new ProjectModel();
        ClockUtil.showWidget(hbox);//clock
        hamburgerMenu(); //hamburger
        hamburgerButtons(); //buttons in hamburger
        showProjects();
    }
}
