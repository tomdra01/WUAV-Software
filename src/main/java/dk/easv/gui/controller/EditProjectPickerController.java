package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXComboBox;
import dk.easv.be.Project;
import dk.easv.bll.util.PopupUtil;
import dk.easv.bll.util.ProjectListCell;
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
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
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
public class EditProjectPickerController implements Initializable {
    @FXML private JFXComboBox<Project> projectComboBox;
    private ProjectModel projectModel;
    private BorderPane borderPane;

    public void setProjectModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
        setProjectComboBox();
    }

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    private void setProjectComboBox() {
        List<Project> projects = projectModel.getProjects();
        ObservableList<Project> observableList = FXCollections.observableArrayList(projects);
        projectComboBox.setItems(observableList);

        projectComboBox.setCellFactory(lv -> new ProjectListCell());
        projectComboBox.setButtonCell(projectComboBox.getCellFactory().call(null));

        projectComboBox.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.EDIT_PROJECT.getView()));
                Parent root = loader.load();

                Project selectedProject = projectComboBox.getValue();

                EditProjectController editProjectController = loader.getController();
                editProjectController.setProject(selectedProject);
                editProjectController.setProjectModel(projectModel);

                Stage window = (Stage) projectComboBox.getScene().getWindow();
                window.setTitle("Edit project window");
                Scene scene = new Scene(root);
                window.setScene(scene);
            } catch (IOException e) {
                PopupUtil.showAlert("Something went wrong", "Failed to switch the scene", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        });
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
