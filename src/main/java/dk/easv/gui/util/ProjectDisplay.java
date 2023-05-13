package dk.easv.gui.util;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.controller.ProjectTemplateController;
import dk.easv.gui.model.ProjectModel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

// java imports
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectDisplay {
    private List<Project> projects;

    public void refresh(HBox projectsHbox, JFXComboBox<String> filterComboBox, JFXTextField searchBar, ProjectModel projectModel, BorderPane mainPane) {
        projectsHbox.getChildren().clear();

        try {projectModel.loadProjects();} catch (Exception e) {throw new RuntimeException(e);}
        projects = projectModel.getProjects();

        String selectedFilter = String.valueOf(filterComboBox.getValue());
        String searchText = searchBar.getText().toLowerCase();
        List<Project> filteredProjects = projects.stream()
                .filter(project -> (selectedFilter == null || "All".equals(selectedFilter) || project.getBusinessType().equals(selectedFilter)) &&
                        (searchText.isEmpty() || project.getName().toLowerCase().contains(searchText) || project.getLocation().toLowerCase().contains(searchText)))
                .toList();

        int limit = 10;
        int counter = 0;

        for (Project project : filteredProjects) {
            if (counter == limit) {
                break;
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_CARD.getView()));
                loader.setControllerFactory(clazz -> new ProjectTemplateController(project));
                AnchorPane root = loader.load();

                ProjectTemplateController projectTemplateController = loader.getController();

                // setting the image
                ByteArrayInputStream inputStream = new ByteArrayInputStream(project.getDrawing());
                Image image = new Image(inputStream);
                ImageView projectImg = projectTemplateController.getProjectImg();
                projectImg.setImage(image);

                //setting project details
                projectTemplateController.getNameLabel().setText(project.getName());
                projectTemplateController.getLocationLabel().setText(project.getLocation());
                projectTemplateController.getDateLabel().setText(String.valueOf(project.getDate()));
                projectTemplateController.getTextLabel().setText(project.getDescription());
                projectTemplateController.getApprovedProject().setSelected(project.isApproved());
                projectTemplateController.setMainPane(mainPane);
                projectTemplateController.setModel(projectModel);

                HBox hbox = new HBox(root);
                projectsHbox.getChildren().add(hbox);
                HBox.setMargin(hbox, new Insets(0, 10, 0, 0));
            } catch (IOException e) {
                throw new GUIException("Failed to show filtered projects", e);
            }
            counter++;
        }
    }

    public void showTechnicianProjects(HBox projectsHbox, JFXComboBox<String> filterComboBox, JFXTextField searchBar, ProjectModel projectModel, BorderPane mainPane, User user) {
        projectsHbox.getChildren().clear();

        try {projectModel.loadTechnicianProjects(user);} catch (DatabaseException e) {throw new RuntimeException(e);}
        projects = projectModel.getTechnicianProjects();

        String selectedFilter = String.valueOf(filterComboBox.getValue());
        String searchText = searchBar.getText().toLowerCase();
        List<Project> filteredProjects = projects.stream()
                .filter(project -> (selectedFilter == null || "All".equals(selectedFilter) || project.getBusinessType().equals(selectedFilter)) &&
                        (searchText.isEmpty() || project.getName().toLowerCase().contains(searchText) || project.getLocation().toLowerCase().contains(searchText)))
                .toList();

        int limit = 10;
        int counter = 0;

        for (Project project : filteredProjects) {
            if (counter == limit) {
                break;
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_CARD.getView()));
                loader.setControllerFactory(clazz -> new ProjectTemplateController(project));
                AnchorPane root = loader.load();

                ProjectTemplateController projectTemplateController = loader.getController();

                // setting the image
                ByteArrayInputStream inputStream = new ByteArrayInputStream(project.getDrawing());
                Image image = new Image(inputStream);
                ImageView projectImg = projectTemplateController.getProjectImg();
                projectImg.setImage(image);

                //setting project details
                projectTemplateController.getNameLabel().setText(project.getName());
                projectTemplateController.getLocationLabel().setText(project.getLocation());
                projectTemplateController.getDateLabel().setText(String.valueOf(project.getDate()));
                projectTemplateController.getTextLabel().setText(project.getDescription());
                projectTemplateController.getApprovedProject().setSelected(project.isApproved());
                projectTemplateController.setMainPane(mainPane);
                projectTemplateController.setModel(projectModel);

                HBox hbox = new HBox(root);
                projectsHbox.getChildren().add(hbox);
                HBox.setMargin(hbox, new Insets(0, 10, 0, 0));
            } catch (IOException e) {
                throw new GUIException("Failed to show filtered projects", e);
            }
            counter++;
        }
    }
}
