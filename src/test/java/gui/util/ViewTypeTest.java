package gui.util;

// imports
import dk.easv.gui.util.ViewType;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ViewTypeTest {

    @DisplayName("Test getView method")
    @Test
    public void testGetView() {
        Assertions.assertEquals("/views/login_window.fxml", ViewType.LOGIN.getView());
        Assertions.assertEquals("/views/users/admin_window.fxml", ViewType.ADMIN.getView());
        Assertions.assertEquals("/views/users/technician_window.fxml", ViewType.TECHNICIAN.getView());
        Assertions.assertEquals("/views/users/projectmanager_window.fxml", ViewType.PROJECT_MANAGER.getView());
        Assertions.assertEquals("/views/users/salesman_window.fxml", ViewType.SALESMAN.getView());
        Assertions.assertEquals("/views/create_user_window.fxml", ViewType.CREATE_USER.getView());
        Assertions.assertEquals("/views/add_customer_window.fxml", ViewType.ADD_CUSTOMER.getView());
        Assertions.assertEquals("/views/assign_new_technician_window.fxml", ViewType.ASSIGN_NEW_TECHNICIAN.getView());
        Assertions.assertEquals("/views/project/draw_installation_window.fxml", ViewType.DRAW_INSTALLATION.getView());
        Assertions.assertEquals("/views/project/project_step1_window.fxml", ViewType.PROJECT_STEP1.getView());
        Assertions.assertEquals("/views/project/project_step2_window.fxml", ViewType.PROJECT_STEP2.getView());
        Assertions.assertEquals("/views/project/project_step3_window.fxml", ViewType.PROJECT_STEP3.getView());
        Assertions.assertEquals("/views/project/project_step4_window.fxml", ViewType.PROJECT_STEP4.getView());
        Assertions.assertEquals("/views/project/project_step5_window.fxml", ViewType.PROJECT_STEP5.getView());
        Assertions.assertEquals("/views/project/project_stepfinal_window.fxml", ViewType.PROJECT_STEP6.getView());
        Assertions.assertEquals("/views/project_template.fxml", ViewType.PROJECT_CARD.getView());
        Assertions.assertEquals("/views/select_project_window.fxml", ViewType.SELECT_PROJECT.getView());
        Assertions.assertEquals("/views/edit_project_window.fxml", ViewType.EDIT_PROJECT.getView());
        Assertions.assertEquals("/views/documentation/internal_documentation.fxml", ViewType.INTERNAL.getView());
        Assertions.assertEquals("/views/documentation/external_documentation.fxml", ViewType.EXTERNAL.getView());
        Assertions.assertEquals("/views/show_technicians_window.fxml", ViewType.SHOW_TECHNICIANS.getView());
        Assertions.assertEquals("/views/show_customers_window.fxml", ViewType.SHOW_CUSTOMERS.getView());
        Assertions.assertEquals("/views/log_window.fxml", ViewType.LOG.getView());
        Assertions.assertEquals("/views/edit_user_window.fxml", ViewType.EDIT_USER.getView());
    }
}