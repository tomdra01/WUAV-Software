package dk.easv.gui.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.Project;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.controller.project.ProjectStep2Controller;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import dk.easv.gui.util.ViewType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditProjectPickerController implements Initializable {
    @FXML private JFXComboBox<Project> projectComboBox;
    @FXML private Button editBtn;
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

    public void editAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.INSPECT_PROJECT.getView()));
            Parent root = loader.load();

            Project selectedProject = projectComboBox.getValue();

            InspectProjectController inspectProjectController = loader.getController();
            inspectProjectController.setProject(selectedProject);
            inspectProjectController.setModel(projectModel);

            Stage window = (Stage) editBtn.getScene().getWindow();
            window.setTitle("Editing");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to open editing for this project", e);
        }
    }
}
