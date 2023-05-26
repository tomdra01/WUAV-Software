package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXCheckBox;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.bll.util.PopupUtil;
import dk.easv.bll.util.UserSingleton;
import dk.easv.gui.controller.documentation.InternalDocumentationController;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
public class ProjectTemplateController implements Initializable {

    @FXML private ImageView projectImg;
    @FXML private Label nameLabel, dateLabel, locationLabel, textLabel;
    @FXML private JFXCheckBox approvedProject;
    @FXML private BorderPane mainPane;
    private final Project project;
    private ProjectModel projectModel;

    public ImageView getProjectImg() {
        return projectImg;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public Label getLocationLabel() {
        return locationLabel;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public Label getTextLabel() {
        return textLabel;
    }

    public JFXCheckBox getApprovedProject() {
        return approvedProject;
    }

    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    public void setModel(ProjectModel projectModel) {
        this.projectModel=projectModel;
    }

    public ProjectTemplateController(Project project) {
        this.project=project;
    }

    public void openProject(){
        try {
            BlurEffectUtil.applyBlurEffect(mainPane,10);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.INTERNAL.getView()));
            Parent createEventParent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Inspect project");
            stage.setResizable(false);
            Scene scene = new Scene(createEventParent);
            stage.setScene(scene);
            stage.show();

            InternalDocumentationController internalDocumentationController = fxmlLoader.getController();
            internalDocumentationController.setPane(mainPane);
            internalDocumentationController.setOnCloseRequestHandler(stage);
            internalDocumentationController.setProject(project);
            internalDocumentationController.setModel(projectModel);
            User user = UserSingleton.getInstance().getUser();
            System.out.println("template card user: "+user);

        } catch (IOException e) {
            PopupUtil.showAlert("Something went wrong", "Failed to open the project", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
