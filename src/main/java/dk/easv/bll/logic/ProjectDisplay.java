package dk.easv.bll.logic;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.Project;
import dk.easv.bll.exception.GUIException;
import dk.easv.gui.controller.ProjectTemplateController;
import dk.easv.gui.model.ProjectModel;
import dk.easv.gui.util.ViewType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectDisplay {
    public void refresh(HBox projectsHbox, JFXComboBox filterComboBox, JFXTextField searchBar, ProjectModel projectModel, BorderPane mainPane) {
        projectsHbox.getChildren().clear();

        String selectedFilter = String.valueOf(filterComboBox.getValue());
        String searchText = searchBar.getText().toLowerCase();
        List<Project> filteredProjects = projectModel.getProjects().stream()
                .filter(project -> (selectedFilter == null || "All".equals(selectedFilter) || project.getBusinessType().equals(selectedFilter)) &&
                        (searchText.isEmpty() || project.getName().toLowerCase().contains(searchText) || project.getLocation().toLowerCase().contains(searchText)))
                .collect(Collectors.toList());

        int limit = 10;
        int counter = 0;

        for (Project project : filteredProjects) {
            if (counter == limit) {
                break;
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ViewType.PROJECT_CARD.getView()));
                loader.setControllerFactory(clazz -> {
                    ProjectTemplateController controller = new ProjectTemplateController(project);
                    return controller;
                });
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
