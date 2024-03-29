package dk.easv.gui.util;

// imports
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.be.Log;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.gui.controller.ProjectTemplateController;
import dk.easv.gui.model.ProjectModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

// java imports
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectDisplay {
    private List<Project> projects;
    private ProjectModel projectModel;

    public void setModel(ProjectModel projectModel) {this.projectModel = projectModel;}

    public void showAllProjects(HBox projectsHbox, JFXComboBox<String> filterComboBox, JFXTextField searchBar, ProjectModel projectModel, BorderPane mainPane) {
        clearProjectsHbox(projectsHbox);

        loadProjects(projectModel);
        projects = projectModel.getProjects();

        List<Project> filteredProjects = filterProjects(filterComboBox, searchBar, projects);

        int limit = 10;
        int counter = 0;

        for (Project project : filteredProjects) {
            if (counter == limit) {
                break;
            }
            try {
                FXMLLoader loader = createLoader(ViewType.PROJECT_CARD.getView(), project);
                AnchorPane root = loader.load();

                ProjectTemplateController projectTemplateController = loader.getController();
                setProjectDetails(projectTemplateController, project, mainPane, projectModel);
                projectTemplateController.getApprovedProject().setVisible(true);

                HBox hbox = new HBox(root);
                addProjectToProjectsHbox(projectsHbox, hbox);
                HBox.setMargin(hbox, new Insets(0, 10, 0, 0));
            } catch (IOException e) {
                throw new RuntimeException("Failed to show filtered projects", e);
            }
            counter++;
        }
    }

    public void showSalesmanProjects(HBox projectsHbox, JFXComboBox<String> filterComboBox, JFXTextField searchBar, ProjectModel projectModel, BorderPane mainPane) {
        clearProjectsHbox(projectsHbox);

        loadSalesmanProjects(projectModel);
        projects = projectModel.getSalesmenProjects();

        List<Project> filteredProjects = filterProjects(filterComboBox, searchBar, projects);

        int limit = 10;
        int counter = 0;

        for (Project project : filteredProjects) {
            if (counter == limit) {
                break;
            }
            try {
                FXMLLoader loader = createLoader(ViewType.PROJECT_CARD.getView(), project);
                AnchorPane root = loader.load();

                ProjectTemplateController projectTemplateController = loader.getController();
                setProjectDetails(projectTemplateController, project, mainPane, projectModel);
                projectTemplateController.getApprovedProject().setVisible(false);

                HBox hbox = new HBox(root);
                addProjectToProjectsHbox(projectsHbox, hbox);
                HBox.setMargin(hbox, new Insets(0, 10, 0, 0));
            } catch (IOException e) {
                throw new RuntimeException("Failed to show filtered projects", e);
            }
            counter++;
        }
    }

    public void showTechnicianProjects(HBox projectsHbox, JFXComboBox<String> filterComboBox, JFXTextField searchBar, ProjectModel projectModel, BorderPane mainPane, User user) {
        clearProjectsHbox(projectsHbox);

        loadTechnicianProjects(projectModel, user);
        projects = projectModel.getTechnicianProjects();

        List<Project> filteredProjects = filterProjects(filterComboBox, searchBar, projects);

        int limit = 10;
        int counter = 0;

        for (Project project : filteredProjects) {
            if (counter == limit) {
                break;
            }
            try {
                FXMLLoader loader = createLoader(ViewType.PROJECT_CARD.getView(), project);
                AnchorPane root = loader.load();

                ProjectTemplateController projectTemplateController = loader.getController();
                setProjectDetails(projectTemplateController, project, mainPane, projectModel);
                projectTemplateController.getApprovedProject().setVisible(false);

                HBox hbox = new HBox(root);
                addProjectToProjectsHbox(projectsHbox, hbox);
                HBox.setMargin(hbox, new Insets(0, 10, 0, 0));
            } catch (IOException e) {
                throw new RuntimeException("Failed to show filtered projects", e);
            }
            counter++;
        }
    }

    private void clearProjectsHbox(HBox projectsHbox) {
        projectsHbox.getChildren().clear();
    }

    private void loadProjects(ProjectModel projectModel) {
        try {
            projectModel.loadProjects();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTechnicianProjects(ProjectModel projectModel, User user) {
        try {
            projectModel.loadTechnicianProjects(user);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSalesmanProjects(ProjectModel projectModel) {
        try {
            projectModel.loadSalesmenProjects();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Project> filterProjects(JFXComboBox<String> filterComboBox, JFXTextField searchBar, List<Project> projects) {
        String selectedFilter = String.valueOf(filterComboBox.getValue());
        String searchText = searchBar.getText().toLowerCase();
        return projects.stream()
                .filter(project -> (selectedFilter == null || "All".equals(selectedFilter) || project.getBusinessType().equals(selectedFilter)) &&
                        (searchText.isEmpty() || project.getName().toLowerCase().contains(searchText) || project.getLocation().toLowerCase().contains(searchText)))
                .toList();
    }

    private FXMLLoader createLoader(String viewType, Project project) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewType));
        loader.setControllerFactory(clazz -> new ProjectTemplateController(project));
        return loader;
    }

    private void setProjectDetails(ProjectTemplateController projectTemplateController, Project project, BorderPane mainPane, ProjectModel projectModel) {
        // setting the image
        ByteArrayInputStream inputStream = new ByteArrayInputStream(project.getDrawing());
        Image image = new Image(inputStream);
        ImageView projectImg = projectTemplateController.getProjectImg();
        projectImg.setImage(image);

        // setting project details
        projectTemplateController.getNameLabel().setText(project.getName());
        projectTemplateController.getLocationLabel().setText(project.getLocation());
        projectTemplateController.getDateLabel().setText(String.valueOf(project.getDate()));
        projectTemplateController.getTextLabel().setText(project.getDescription());
        projectTemplateController.getApprovedProject().setSelected(project.isApproved());

        projectTemplateController.getApprovedProject().setOnAction(event -> {
            project.setApproved(projectTemplateController.getApprovedProject().isSelected());
            try {
                Log log = new Log("Approved status changed: "+project.getName(), LocalDateTime.now(), "admin");  // > user.getUsername()
                projectModel.updateApprovalStatus(project);
                projectModel.createLogEntry(log);
            } catch (DatabaseException e) {
                PopupUtil.showAlert("Something went wrong", e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        });

        projectTemplateController.setMainPane(mainPane);
        projectTemplateController.setModel(projectModel);
    }

    public void showTableView(HBox hbox, User user) {
        TableView<Project> tableView = new TableView<>();

        tableView.getStylesheets().add(Objects.requireNonNull(PopupUtil.class.getResource("/styles/tableview_style.css")).toExternalForm());
        tableView.setPrefWidth(1200);

        TableColumn<Project, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Project, String> locationColumn = new TableColumn<>("Location");
        TableColumn<Project, String> dateColumn = new TableColumn<>("Date");
        TableColumn<Project, String> descriptionColumn = new TableColumn<>("Description");

        nameColumn.setPrefWidth(150);
        locationColumn.setPrefWidth(150);
        dateColumn.setPrefWidth(150);
        descriptionColumn.setPrefWidth(300);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(locationColumn);
        tableView.getColumns().add(dateColumn);
        tableView.getColumns().add(descriptionColumn);

        switch (user.getRole()) {
            case "Admin", "Project Manager" ->
                    tableView.setItems(FXCollections.observableArrayList(projectModel.getProjects()));
            case "Salesman" ->
                    tableView.setItems(FXCollections.observableArrayList(projectModel.getSalesmenProjects()));
            case "Technician" ->
                    tableView.setItems(FXCollections.observableArrayList(projectModel.getTechnicianProjects()));
            default ->
                    PopupUtil.showAlert("Unable to show projects", "Unable to show projects for the current user", Alert.AlertType.INFORMATION);
        }

        hbox.getChildren().clear();
        hbox.getChildren().add(tableView);
    }

    private void addProjectToProjectsHbox(HBox projectsHbox, HBox hbox) {
        projectsHbox.getChildren().add(hbox);
    }
}