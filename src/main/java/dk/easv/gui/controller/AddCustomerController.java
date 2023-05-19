package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.Customer;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.model.CustomerModel;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// java imports
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 *
 * @author tomdra01, mrtng1
 */
public class AddCustomerController implements Initializable {
    @FXML private BorderPane currentNode, borderPane;
    @FXML private JFXTextField nameField, emailField;
    private CustomerModel customerModel;

    public void setPane(BorderPane mainPane) {
        this.borderPane = mainPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    public void addCustomer() {
        String name = nameField.getText();
        String email = emailField.getText();

        if (!nameField.getText().isEmpty() || !emailField.getText().isEmpty()) {
            if (checkEmailFormat(emailField)){
                Customer customer = new Customer(name, email);

                try {
                    customerModel.createCustomer(customer);
                } catch (DatabaseException e) {
                    PopupUtil.showAlert("Something went wrong", e.getMessage(), Alert.AlertType.ERROR);
                    e.printStackTrace();
                }
            }else {
                PopupUtil.showAlert("Wrong format", "Please make sure that the email has right format", Alert.AlertType.INFORMATION);
            }
        } else {
            PopupUtil.showAlert("Empty fields", "Please fill in all the fields", Alert.AlertType.INFORMATION);
        }
    }

    public static boolean checkEmailFormat(JFXTextField jfxTextField) {
        String email = jfxTextField.getText();

        String emailPattern = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";

        return Pattern.matches(emailPattern, email);
    }

    public void cancelWindow() {
        BlurEffectUtil.removeBlurEffect(borderPane);
        Stage stage = (Stage) currentNode.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerModel = new CustomerModel();
    }
}
