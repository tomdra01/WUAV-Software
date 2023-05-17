package dk.easv.bll.util;

import javafx.scene.control.ListCell;
import dk.easv.be.Project;

public class ProjectListCell extends ListCell<Project> {
    @Override
    protected void updateItem(Project project, boolean empty) {
        super.updateItem(project, empty);
        setText(empty ? "" : project.getName());
    }
}