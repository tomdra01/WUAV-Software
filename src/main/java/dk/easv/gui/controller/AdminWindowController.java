package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXHamburger;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.model.AdminModel;
import dk.easv.gui.model.ProjectManagerModel;
import dk.easv.gui.model.SalesmanModel;
import dk.easv.gui.model.TechnicianModel;
import dk.easv.gui.util.BlurEffectUtil;
import dk.easv.gui.util.ClockUtil;
import dk.easv.gui.util.HamburgerUtil;
import dk.easv.gui.util.ViewType;
import io.github.palexdev.materialfx.controls.MFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML
    private StackPane stackPane;
    @FXML
    private HBox hbox;
    @FXML
    private JFXHamburger jfxHamburger;
    @FXML
    private BorderPane mainPane;
    private AdminModel adminModel;
    private TechnicianModel technicianModel;
    private final Button createUserButton = new Button("Create user");
    private final Button logOutButton = new Button("Log out");
    private final Button showTechniciansButton = new Button("Show technicians");
    private final Button showCustomersButton = new Button("Show customers");
    private final Button showLogButton = new Button("See log");

    public void setModel(AdminModel adminModel, TechnicianModel technicianModel) {
        this.adminModel = adminModel;
        this.technicianModel = technicianModel;

        try {
            technicianModel.loadTechnicians();
        } catch (DatabaseException e) {
            throw new GUIException("Failed while loading technicians", e);
        }
    }

    /**
     * Initializing hamburger menu.
     */
    private void hamburgerMenu(){
        VBox leftVBox = new VBox();
        leftVBox.setPrefWidth(160);
        leftVBox.getStylesheets().add("styles/main_style.css");

        createUserButton.setMaxWidth(200);
        showTechniciansButton.setMaxWidth(200);
        showCustomersButton.setMaxWidth(200);
        showLogButton.setMaxWidth(200);
        logOutButton.setMaxWidth(200);

        leftVBox.getChildren().addAll(createUserButton, showTechniciansButton, showCustomersButton, showLogButton, logOutButton);
        VBox.setMargin(createUserButton, new Insets(60, 20, 0, 20));
        VBox.setMargin(showTechniciansButton, new Insets(20, 20, 0, 20));
        VBox.setMargin(showCustomersButton, new Insets(20, 20, 0, 20));
        VBox.setMargin(showLogButton, new Insets(20, 20, 0, 20));
        VBox.setMargin(logOutButton, new Insets(240, 20, 0, 20));

        HamburgerUtil.showHamburger(jfxHamburger, leftVBox, mainPane);
    }

    /**
     * Actions for buttons inside hamburger menu.
     */
    private void hamburgerButtons() {
        // technician listView
        showTechniciansButton.setOnAction(event -> {
            MFXListView<Technician> mfxListView = new MFXListView<>();

            stackPane.getChildren().addAll(mfxListView);
            StackPane.setMargin(mfxListView, new Insets(20, 200, 20, 200));

            mfxListView.getItems().addAll(technicianModel.getTechnicians());
        });

        // log out functionality
        logOutButton.setOnAction(event -> {
            try {
                Stage currentStage = (Stage) mainPane.getScene().getWindow();
                currentStage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.LOGIN.getPath()));
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

        createUserButton.setOnAction(event -> {
            try {
                openCreateUser();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void openCreateUser() throws IOException {
        BlurEffectUtil.applyBlurEffect(mainPane,10);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/create_user_window.fxml"));
        Parent createEventParent = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Create User");
        stage.setResizable(false);
        Scene scene = new Scene(createEventParent);
        stage.setScene(scene);

        CreateUserWindowController createUserWindowController = fxmlLoader.getController();
        createUserWindowController.setTechModel(new TechnicianModel());
        createUserWindowController.setSalModel(new SalesmanModel());
        createUserWindowController.setPmModel(new ProjectManagerModel());
        createUserWindowController.setPane(mainPane);
        createUserWindowController.setOnCloseRequestHandler(stage);

        stage.show();
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hamburgerMenu(); // hamburger
        hamburgerButtons(); // buttons in hamburger
        ClockUtil.showWidget(hbox); // clock
    }
}