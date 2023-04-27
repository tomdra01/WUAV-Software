package dk.easv.gui.util;

/**
 *
 * @author tomdra01, mrtng1
 */
public enum ViewType {
    LOGIN("/views/login_window.fxml"),
    ADMIN("/views/admin_window.fxml"),
    TECHNICIAN("/views/technician_window.fxml"),
    NEWPROJECT("/views/new_project_window.fxml");

    private String path;

    private ViewType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
