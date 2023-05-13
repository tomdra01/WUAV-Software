package dk.easv.gui.controller.project;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.User;
import dk.easv.bll.exception.GUIException;
import dk.easv.bll.util.ImageByteReader;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// java imports
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectStep5Controller implements Initializable {
    @FXML private Button nextStepBtn, previousStepBtn, addImagesBtn;
    @FXML private ImageView imageView1, imageView2, imageView3;
    private String projectName, businessType, projectLocation, projectDescription;
    private LocalDate projectDate;
    private byte[] projectDrawing;
    private byte[] projectPhoto1, projectPhoto2, projectPhoto3;
    private Image img1, img2, img3;
    private ProjectModel projectModel;
    private HBox projectHbox;
    private JFXComboBox<String> filterComboBox;
    private JFXTextField searchBar;
    private BorderPane mainPane;
    private User user;

    public void setMainPage(HBox projectHbox, JFXComboBox<String> filterComboBox, JFXTextField searchBar, BorderPane mainPane){
        this.projectHbox=projectHbox;
        this.filterComboBox=filterComboBox;
        this.searchBar=searchBar;
        this.mainPane=mainPane;
    }

    public void setModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProject(String projectName, String businessType, String projectLocation, LocalDate projectDate, byte[] projectDrawing, String projectDescription) {
        this.projectName = projectName;
        this.businessType = businessType;
        this.projectLocation = projectLocation;
        this.projectDate = projectDate;
        this.projectDrawing = projectDrawing;
        this.projectDescription = projectDescription;
    }

    public void setImages(Image img1, Image img2, Image img3, byte[] projectPhoto1, byte[] projectPhoto2, byte[] projectPhoto3){
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.projectPhoto1 = projectPhoto1;
        this.projectPhoto2 = projectPhoto2;
        this.projectPhoto3 = projectPhoto3;

        imageView1.setImage(img1);
        imageView2.setImage(img2);
        imageView3.setImage(img3);
    }

    public void addImages() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select images");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("image file formats", "*.jpg", "*.jpeg", "*.png"));

        Stage stage = (Stage) addImagesBtn.getScene().getWindow();

        List<File> selectedFiles = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                selectedFiles.add(selectedFile);
            } else {
                break;
            }
        }

        if (!selectedFiles.isEmpty()) {
            for (int i = 0; i < selectedFiles.size(); i++) {
                File file = selectedFiles.get(i);
                long filesize;

                Image image = new Image(file.toURI().toString());
                switch (i) {
                    case 0 -> {
                        imageView1.setImage(image);
                        img1 = image;
                        projectPhoto1 = ImageByteReader.readImage(file);
                        filesize = projectPhoto1.length;
                        System.out.println(filesize);
                    }
                    case 1 -> {
                        imageView2.setImage(image);
                        img2 = image;
                        projectPhoto2 = ImageByteReader.readImage(file);
                        filesize = projectPhoto2.length;
                        System.out.println(filesize);
                    }
                    case 2 -> {
                        imageView3.setImage(image);
                        img3 = image;
                        projectPhoto3 = ImageByteReader.readImage(file);
                        filesize = projectPhoto3.length;
                        System.out.println(filesize);
                    }
                }
            }
        } else {
            throw new GUIException("No file selected");
        }
    }

    public void nextStep(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP6.getView()));
            Parent root = loader.load();

            ProjectStepFinalController projectStepFinalController = loader.getController();
            projectStepFinalController.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            projectStepFinalController.setImages(projectPhoto1, projectPhoto2, projectPhoto3);
            projectStepFinalController.setModel(projectModel);
            projectStepFinalController.setUser(user);
            projectStepFinalController.setMainPage(projectHbox, filterComboBox, searchBar, mainPane);

            Stage window = (Stage) nextStepBtn.getScene().getWindow();
            window.setTitle("Step 6");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to change the window", e);
        }
    }

    public void previousStep() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_STEP4.getView()));
            Parent root = loader.load();

            ProjectStep4Controller projectStep4 = loader.getController();
            projectStep4.setProject(projectName, businessType, projectLocation, projectDate, projectDrawing, projectDescription);
            projectStep4.setImages(img1, img2, img3, projectPhoto1, projectPhoto2, projectPhoto3);
            projectStep4.setModel(projectModel);
            projectStep4.setUser(user);

            Stage window = (Stage) previousStepBtn.getScene().getWindow();
            window.setTitle("Step 4");
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (IOException e) {
            throw new GUIException("Failed to change the window", e);
        }
    }

    /**
     * Initialize method
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
