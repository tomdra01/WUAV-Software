package be;

// imports
import dk.easv.be.User;
import dk.easv.be.UserSingleton;

// JUnit imports
import org.junit.Test;

// static imports
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author tomdra01, mrtng1
 */
public class UserSingletonTest {

    @Test
    public void testGetInstance() {
        UserSingleton instance1 = UserSingleton.getInstance();
        UserSingleton instance2 = UserSingleton.getInstance();

        assertNotNull(instance1);
        assertNotNull(instance2);
        assertEquals(instance1, instance2);
    }

    @Test
    public void testGetSetUser() {
        UserSingleton instance = UserSingleton.getInstance();
        User user = new User("testUser", "testPassword", "Technician"); // Assuming User class exists

        instance.setUser(user);
        User retrievedUser = instance.getUser();

        assertNotNull(retrievedUser);
        assertEquals(user, retrievedUser);
    }
}