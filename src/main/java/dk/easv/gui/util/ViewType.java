package dk.easv.gui.util;

/**
 *
 * @author tomdra01, mrtng1
 */
public enum ViewType {
    LOGIN("/views/login_window.fxml"),
    ADMIN("/views/users/admin_window.fxml"),
    TECHNICIAN("/views/users/technician_window.fxml"),
    PROJECT_MANAGER("/views/users/projectmanager_window.fxml"),
    SALESMAN("/views/users/salesman_window.fxml"),
    CREATE_USER("/views/create_user_window.fxml"),
    DRAW_INSTALLATION("/views/project/draw_installation_window.fxml"),
    PROJECT_STEP1("/views/project/project_step1_window.fxml"),
    PROJECT_STEP2("/views/project/project_step2_window.fxml"),
    PROJECT_STEP3("/views/project/project_step3_window.fxml"),
    PROJECT_STEP4("/views/project/project_step4_window.fxml"),
    PROJECT_STEP5("/views/project/project_step5_window.fxml"),
    PROJECT_STEP6("/views/project/project_stepfinal_window.fxml"),
    PROJECT_CARD("/views/project_template.fxml"),
    INSPECT_PROJECT("/views/inspect_project.fxml"),
    EDIT_PICK_MENU("/views/edit_project_picker.fxml"),
    EDIT_PROJECT("views/edit_project.fxml");

    private final String path;

    private ViewType(String path) {
        this.path = path;
    }

    public String getView() {
        return path;
    }
}
