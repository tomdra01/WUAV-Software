package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXListView;
import dk.easv.be.Customer;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.CustomerModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// java imports
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ShowCustomersController implements Initializable {
    @FXML private JFXListView<Customer> customersList;
    private BorderPane mainPane;
    private CustomerModel customerModel;

    public void setModel(CustomerModel customerModel) {
        this.customerModel = customerModel;

        try {
            customerModel.loadCustomers();
        } catch (DatabaseException e) {
            PopupUtil.showAlert("Something went wrong", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        customersList.setItems(customerModel.getCustomers());
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
