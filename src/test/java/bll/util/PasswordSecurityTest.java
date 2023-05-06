package bll.util;

// static imports
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

// imports
import dk.easv.bll.util.PasswordSecurity;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author tomdra01, mrtng1
 */
public class PasswordSecurityTest {

    @DisplayName("Test password hash")
    @Test
    public void testHashPassword() {
        String password = "myPassword123";

        String hashedPassword1 = PasswordSecurity.hashPassword(password);
        String hashedPassword2 = PasswordSecurity.hashPassword(password);

        assertNotNull(hashedPassword1);
        assertNotNull(hashedPassword2);
        assertEquals(hashedPassword1, hashedPassword2);
    }
}
