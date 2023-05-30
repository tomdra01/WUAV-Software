package dk.easv.gui.controller;

// imports
import dk.easv.be.Log;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.BlurEffectUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// java imports
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class LogWindowController implements Initializable {
    private BorderPane borderPane;
    @FXML
    private TableView<Log> logTable;
    @FXML
    private TableColumn<Log, String> userColumn;
    @FXML
    private TableColumn<Log, String> actionColumn;
    @FXML
    private TableColumn<Log, LocalDateTime> timestampColumn;

    public void setProjectModel(ProjectModel projectModel) {

        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("logAction"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("actionTime"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timestampColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.format(formatter));
            }
        });

        try {
            logTable.getItems().setAll(projectModel.getAllLogs());
            System.out.println(projectModel.getAllLogs());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPane(BorderPane mainPane) {
        this.borderPane = mainPane;
    }

    public void setOnCloseRequestHandler(Stage stage) {
        stage.setOnCloseRequest(event -> BlurEffectUtil.removeBlurEffect(borderPane));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
