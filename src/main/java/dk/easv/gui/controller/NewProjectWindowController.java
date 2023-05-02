package dk.easv.gui.controller;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.bll.util.PopupUtil;
import dk.easv.gui.util.BlurEffectUtil;
import dk.easv.gui.util.LoaderUtil;
import dk.easv.gui.util.ViewType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// java imports
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class NewProjectWindowController implements Initializable {
    @FXML private JFXTextField pNameField;
    @FXML private JFXTextField pLocationField;
    @FXML private DatePicker pDatePicker;
    @FXML private JFXComboBox<String> pBusinessComboBox;
    @FXML private BorderPane currentPane;
    @FXML private Button nextStepBtn1, nextStepBtn2, previousStepBtn2;
    private BorderPane borderPane;
    private Scene scene;
    private String projectName, businessType, projectLocation;
    private LocalDate projectDate;

    public void setPane(BorderPane borderPane) {
        this.borderPane = borderPane;
        pBusinessComboBox.setItems(FXCollections.observableArrayList("Consumer", "Corporate & Government"));
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void nextStep1() {
        projectName = pNameField.getText();
        businessType = pBusinessComboBox.getValue();
        LoaderUtil.loadFXML(ViewType.PROJECT_STEP2.getView(), nextStepBtn1, "Step 2", scene);
    }

    public void previousStep2() {
        LoaderUtil.loadFXML(ViewType.PROJECT_STEP1.getView(), previousStepBtn2, "Step 1", scene);
    }

    public void nextStep2() {
        projectLocation = pLocationField.getText();
        LoaderUtil.loadFXML(ViewType.PROJECT_STEP3.getView(), nextStepBtn2, "Step 3", scene);
    }
}
