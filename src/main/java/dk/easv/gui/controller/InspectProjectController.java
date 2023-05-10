package dk.easv.gui.controller;

import dk.easv.be.Project;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InspectProjectController implements Initializable {
    @FXML private Button deleteButton;
    private BorderPane borderPane;
    private ProjectModel projectModel;
    private Project project;

    public void setProject(Project project){
        this.project=project;
    }

    public void setModel(ProjectModel projectModel){
        this.projectModel=projectModel;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void deleteProject(){
        try {
            projectModel.deleteProject(project);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
