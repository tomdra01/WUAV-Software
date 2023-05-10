package dk.easv.gui.controller;

// imports
import dk.easv.be.Project;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML private Button openButton;
    @FXML private BorderPane mainPane;
    private Project project;
    private ProjectModel projectModel;

    public ImageView getProjectImg() {
        return projectImg;
    }
    public Label getNameLabel() {return nameLabel;}
    public Label getLocationLabel() {return locationLabel;}
    public Label getDateLabel() {return dateLabel;}
    public Label getTextLabel() {return textLabel;}
    public void setMainPane(BorderPane mainPane){this.mainPane=mainPane;}
    public void setModel(ProjectModel projectModel){
        this.projectModel=projectModel;
    }
    public ProjectTemplateController(){

    }

    public ProjectTemplateController(Project project){
        this.project=project;
    }

    public void openProject(){
        BlurEffectUtil.applyBlurEffect(mainPane,10);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ViewType.INSPECT_PROJECT.getView()));
            Parent createEventParent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Inspect project");
            stage.setResizable(false);
            Scene scene = new Scene(createEventParent);
            stage.setScene(scene);
            stage.show();

            InspectProjectController inspectProjectController = fxmlLoader.getController();
            inspectProjectController.setPane(mainPane);
            inspectProjectController.setOnCloseRequestHandler(stage);
            inspectProjectController.setProject(project);
            inspectProjectController.setModel(projectModel);

        } catch (IOException e) {
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
