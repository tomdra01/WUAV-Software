package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXListView;
import dk.easv.be.User;
import dk.easv.gui.model.UserModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// java imports
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
}
