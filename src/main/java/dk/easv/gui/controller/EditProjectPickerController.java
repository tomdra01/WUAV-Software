package dk.easv.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.Project;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditProjectPickerController implements Initializable {
    @FXML private JFXComboBox<Project> projectComboBox;
    private ProjectModel projectModel;
    private BorderPane borderPane;



    public void setProjectModel(ProjectModel projectModel) {
        this.projectModel=projectModel;

        List<Project> projects = projectModel.getProjects();
        ObservableList<Project> observableList = FXCollections.observableArrayList(projects);
        projectComboBox.setItems(observableList);
    }

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
