package dk.easv.gui.util;

/**
 *
 * @author tomdra01, mrtng1
 */
public enum ViewType {
    LOGIN("/views/login_window.fxml"),
    ADMIN("/views/admin_window.fxml"),
    TECHNICIAN("/views/technician_window.fxml"),
    CREATE_USER("/views/create_user_window.fxml"),
    DRAW_INSTALLATION("/views/draw_installation_window.fxml"),
    PROJECT_STEP1("/views/project/Step1_project_info.fxml"),
    PROJECT_STEP2("/views/project/Step2_project_details.fxml"),
    PROJECT_STEP3("/views/project/Step3_project_drawing.fxml"),
    PROJECT_STEP4("/views/project/Step4_project_installation.fxml"),
    PROJECT_STEP5("/views/project/Step5_project_photos.fxml"),
    PROJECT_STEP6("/views/project/Step6_final.fxml");
    private final String path;

    private ViewType(String path) {
        this.path = path;
    }

    public String getView() {
        return path;
    }
}
