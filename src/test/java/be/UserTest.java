package be;

// imports
import dk.easv.be.User;

// JUnit imports
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author tomdra01, mrtng1
 */
public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User(1, "testUser", "testPassword", "Admin");
    }

    @DisplayName("Test user getter")
    @Test
    public void testUserGetter() {
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("testUser", user.getUsername());
        Assertions.assertEquals("testPassword", user.getPassword());
        Assertions.assertEquals("Admin", user.getRole());
    }

    @DisplayName("Test user setter")
    @Test
    public void testUserSetter() {
        user.setId(2);
        user.setUsername("newUser");
        user.setPassword("newPassword");
        user.setRole("Technician");

        Assertions.assertEquals(2, user.getId());
        Assertions.assertEquals("newUser", user.getUsername());
        Assertions.assertEquals("newPassword", user.getPassword());
        Assertions.assertEquals("Technician", user.getRole());
    }

    @DisplayName("Test user with null values")
    @Test
    public void testUserWithNullValues() {
        user.setUsername(null);
        user.setPassword(null);
        user.setRole(null);

        Assertions.assertNull(user.getUsername());
        Assertions.assertNull(user.getPassword());
        Assertions.assertNull(user.getRole());
    }

    @DisplayName("Test user to string")
    @Test
    public void testUserToString() {
        User user = new User(1, "testUser", "testPassword", "Admin");
        String expectedToString = "User{id=1, username='testUser', password='testPassword', role='Admin'}";
        Assertions.assertEquals(expectedToString, user.toString());
    }
}
