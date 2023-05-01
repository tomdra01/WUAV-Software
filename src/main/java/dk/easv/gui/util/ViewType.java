package dk.easv.gui.util;

/**
 *
 * @author tomdra01, mrtng1
 */
public enum ViewType {
    LOGIN("/views/login_window.fxml"),
    ADMIN("/views/admin_window.fxml"),
    TECHNICIAN("/views/technician_window.fxml"),
    NEW_PROJECT("/views/new_project_window.fxml"),
    CREATE_USER("/views/create_user_window.fxml"),
    PROJECT_STEP1("/views/project/project_baseinfo.fxml"),
    PROJECT_STEP2("/views/project/project_secondaryinfo.fxml"),
    PROJECT_STEP3("/views/project/project_scheme_type.fxml");
    private final String path;

    private ViewType(String path) {
        this.path = path;
    }

    public String getView() {
        return path;
    }
}
