package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXListView;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.UserModel;
import dk.easv.gui.util.BlurEffectUtil;
import dk.easv.gui.util.ViewType;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ShowTechniciansController implements Initializable {
    @FXML private JFXListView<User> techniciansList;
    private BorderPane mainPane;
    private UserModel userModel;

    public void setModel(UserModel userModel) {
        this.userModel = userModel;
        techniciansList.setItems(userModel.getTechnicians());
    }

    public void setPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(mainPane));
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void editUserButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.EDIT_USER.getView()));
            Parent root = loader.load();

            User selectedUser = techniciansList.getSelectionModel().getSelectedItem();

            if (selectedUser == null) {
                PopupUtil.showAlert("No User Selected", "Please select a user from the list", Alert.AlertType.WARNING);
                return;
            }

            EditUserWindowController editUserWindowController = loader.getController();
            editUserWindowController.setUser(selectedUser);
            editUserWindowController.setUserModel(userModel);
            editUserWindowController.setPane(mainPane);

            Stage window = (Stage) techniciansList.getScene().getWindow();
            window.setTitle("Edit User Window");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            PopupUtil.showAlert("Something went wrong", "Failed to switch the scene", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
