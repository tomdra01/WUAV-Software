package dk.easv.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import dk.easv.be.Project;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import dk.easv.gui.util.ViewType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditProjectPickerController implements Initializable {
    @FXML private JFXComboBox<Project> projectComboBox;
    private ProjectModel projectModel;
    private BorderPane borderPane;

    public void setProjectModel(ProjectModel projectModel) {
        this.projectModel = projectModel;

        List<Project> projects = projectModel.getProjects();
        ObservableList<Project> observableList = FXCollections.observableArrayList(projects);
        projectComboBox.setItems(observableList);

        projectComboBox.setCellFactory(lv -> new ListCell<Project>() {
            @Override
            protected void updateItem(Project project, boolean empty) {
                super.updateItem(project, empty);
                setText(empty ? "" : project.getName());
            }
        });
        projectComboBox.setButtonCell(projectComboBox.getCellFactory().call(null));
        editSelectedProject();
    }

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    public void editSelectedProject() {
        projectComboBox.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.EDIT_PROJECT.getView()));
                Parent root = loader.load();

                Project selectedProject = projectComboBox.getValue();

                EditProjectController editProjectController = loader.getController();
                editProjectController.setProject(selectedProject);
                editProjectController.setProjectModel(projectModel);

                Stage window = (Stage) projectComboBox.getScene().getWindow();
                window.setTitle("Editing");
                Scene scene = new Scene(root);
                window.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException("Failed to open editing for this project", e);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
