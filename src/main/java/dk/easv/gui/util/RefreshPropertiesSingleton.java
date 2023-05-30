package dk.easv.gui.util;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dk.easv.gui.model.ProjectModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class RefreshPropertiesSingleton {
    private static RefreshPropertiesSingleton instance;

    private HBox projectsHbox;
    private JFXComboBox<String> filterComboBox;
    private JFXTextField searchBar;
    private ProjectModel projectModel;
    private BorderPane mainPane;

    private RefreshPropertiesSingleton() {}

    public static RefreshPropertiesSingleton getInstance() {
        if (instance == null) {
            instance = new RefreshPropertiesSingleton();
        }
        return instance;
    }

    public HBox getProjectsHbox() {
        return projectsHbox;
    }

    public void setProjectsHbox(HBox projectsHbox) {
        this.projectsHbox = projectsHbox;
    }

    public JFXComboBox<String> getFilterComboBox() {
        return filterComboBox;
    }

    public void setFilterComboBox(JFXComboBox<String> filterComboBox) {
        this.filterComboBox = filterComboBox;
    }

    public JFXTextField getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(JFXTextField searchBar) {
        this.searchBar = searchBar;
    }

    public ProjectModel getProjectModel() {
        return projectModel;
    }

    public void setProjectModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public BorderPane getMainPane() {
        return mainPane;
    }

    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }
}
