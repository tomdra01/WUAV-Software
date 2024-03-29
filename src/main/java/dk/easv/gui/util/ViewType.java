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
    ADD_CUSTOMER("/views/add_customer_window.fxml"),
    ASSIGN_NEW_TECHNICIAN("/views/assign_new_technician_window.fxml"),
    DRAW_INSTALLATION("/views/project/draw_installation_window.fxml"),
    PROJECT_STEP1("/views/project/project_step1_window.fxml"),
    PROJECT_STEP2("/views/project/project_step2_window.fxml"),
    PROJECT_STEP3("/views/project/project_step3_window.fxml"),
    PROJECT_STEP4("/views/project/project_step4_window.fxml"),
    PROJECT_STEP5("/views/project/project_step5_window.fxml"),
    PROJECT_STEP6("/views/project/project_stepfinal_window.fxml"),
    PROJECT_CARD("/views/project_template.fxml"),
    SELECT_PROJECT("/views/select_project_window.fxml"),
    EDIT_PROJECT("/views/edit_project_window.fxml"),
    INTERNAL("/views/documentation/internal_documentation.fxml"),
    EXTERNAL("/views/documentation/external_documentation.fxml"),
    SHOW_TECHNICIANS("/views/show_technicians_window.fxml"),
    SHOW_CUSTOMERS("/views/show_customers_window.fxml"),
    LOG("/views/log_window.fxml"),
    EDIT_USER("/views/edit_user_window.fxml");

    private final String path;

    ViewType(String path) {
        this.path = path;
    }

    public String getView() {
        return path;
    }
}
