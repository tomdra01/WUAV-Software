package dal.DAO;

// imports
import dk.easv.be.Log;
import dk.easv.be.Project;
import dk.easv.be.User;
import dk.easv.dal.dao.ProjectDAO;

// JUnit imports
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

// java imports
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// static imports
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectDAOTest {
    private ProjectDAO projectDAO;

    @Before
    public void setup() {
        projectDAO = new ProjectDAO();
    }

    @DisplayName("Test read all projects")
    @Test
    public void testReadAllProjects() {
        try {
            List<Project> projects = projectDAO.readAllProjects();
            assertNotNull(projects);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test read technician projects")
    @Test
    public void testReadTechnicianProjects() {
        try {
            User user = new User(1, "technician", "password", "technician");
            List<Project> technicianProjects = projectDAO.readTechnicianProjects(user);
            assertNotNull(technicianProjects);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test read salesman projects")
    @Test
    public void testReadSalesmanProjects() {
        try {
            List<Project> salesmanProjects = projectDAO.readSalesmanProjects();
            assertNotNull(salesmanProjects);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test get project images")
    @Test
    public void testGetProjectImages() {
        try {
            int projectId = 1;
            List<byte[]> projectImages = projectDAO.getProjectImages(projectId);
            assertNotNull(projectImages);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test create project")
    @Test
    public void testCreateProject() {
        try {
            Project project = new Project(0, "Project Name", "Business Type", "Location", LocalDate.now(), null, "Description", false);
            Project createdProject = projectDAO.createProject(project);
            assertNotNull(createdProject);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test insert images")
    @Test
    public void testInsertImages() {
        try {
            Project project = new Project(1, "Project Name", "Business Type", "Location", LocalDate.now(), null, "Description", false);
            byte[] imageData = new byte[10];
            projectDAO.insertImages(project, imageData);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test assign technician to project")
    @Test
    public void testTechnicianProject() {
        try {
            User user = new User(1, "technician", "password", "technician");
            Project project = new Project(1, "Project Name", "Business Type", "Location", LocalDate.now(), null, "Description", false);
            projectDAO.technicianProject(user, project);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test delete project")
    @Test
    public void testDeleteProject() {
        try {
            Project project = new Project(1, "Project Name", "Business Type", "Location", LocalDate.now(), null, "Description", false);
            projectDAO.deleteProject(project);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test create log")
    @Test
    public void testCreateLogEntry() {
        try {
            Log log = new Log("Action", LocalDateTime.now(), "username");
            projectDAO.createLogEntry(log);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @DisplayName("Test get all logs")
    @Test
    public void testGetAllLogs() {
        try {
            List<Log> logs = projectDAO.getAllLogs();
            assertNotNull(logs);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}