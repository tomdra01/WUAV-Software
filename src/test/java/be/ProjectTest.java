package be;

// imports
import dk.easv.be.Project;

// JUnit imports
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

// java imports
import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectTest {
    private Project project;

    @Before
    public void setUp() {
        project = new Project(1, "Test Project", "Business Type", "Location", LocalDate.now(), new byte[0], "Description", true);
    }

    @DisplayName("Test project getter")
    @Test
    public void testProjectGetter() {
        Assertions.assertEquals(1, project.getId());
        Assertions.assertEquals("Test Project", project.getName());
        Assertions.assertEquals("Business Type", project.getBusinessType());
        Assertions.assertEquals("Location", project.getLocation());
        Assertions.assertEquals(LocalDate.now(), project.getDate());
        Assertions.assertArrayEquals(new byte[0], project.getDrawing());
        Assertions.assertEquals("Description", project.getDescription());
        Assertions.assertTrue(project.isApproved());
    }

    @DisplayName("Test project setter")
    @Test
    public void testProjectSetter() {
        project.setId(2);
        project.setName("New Project");
        project.setBusinessType("New Business Type");
        project.setLocation("New Location");
        LocalDate newDate = LocalDate.of(2023, 1, 1);
        project.setDate(newDate);
        byte[] newDrawing = new byte[]{1, 2, 3};
        project.setDrawing(newDrawing);
        project.setDescription("New Description");
        project.setApproved(false);

        Assertions.assertEquals(2, project.getId());
        Assertions.assertEquals("New Project", project.getName());
        Assertions.assertEquals("New Business Type", project.getBusinessType());
        Assertions.assertEquals("New Location", project.getLocation());
        Assertions.assertEquals(newDate, project.getDate());
        Assertions.assertEquals(newDrawing, project.getDrawing());
        Assertions.assertEquals("New Description", project.getDescription());
        Assertions.assertFalse(project.isApproved());
    }

    @DisplayName("Test project with null values")
    @Test
    public void testProjectWithNullValues() {
        project.setName(null);
        project.setBusinessType(null);
        project.setLocation(null);
        project.setDate(null);
        project.setDrawing(null);
        project.setDescription(null);

        Assertions.assertNull(project.getName());
        Assertions.assertNull(project.getBusinessType());
        Assertions.assertNull(project.getLocation());
        Assertions.assertNull(project.getDate());
        Assertions.assertNull(project.getDrawing());
        Assertions.assertNull(project.getDescription());
    }

    @DisplayName("Test project to string")
    @Test
    public void testProjectToString() {
        Project project = new Project(1, "Test Project", "Business Type", "Location", LocalDate.now(), new byte[0], "Description", true);
        String expectedToString = "Test Project";
        Assertions.assertEquals(expectedToString, project.toString());
    }
}