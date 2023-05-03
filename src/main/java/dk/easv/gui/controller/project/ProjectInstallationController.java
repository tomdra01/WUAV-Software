package dk.easv.gui.controller.project;

// imports
import javafx.fxml.Initializable;

// java imports
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectInstallationController implements Initializable {
    private String projectName, businessType, projectLocation;
    private LocalDate projectDate;

    public void setFields(String projectName, String businessType, String projectLocation, LocalDate projectDate) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;

        System.out.println("Project name: " +projectName
                +"\nBusiness type: " +businessType
                +"\nProject location: " +projectLocation
                +"\nProject date: " +projectDate
        );
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
