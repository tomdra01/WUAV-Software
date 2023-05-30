package bll.util;

// imports
import dk.easv.bll.util.PasswordSecurity;

// JUnit imports
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

// static imports
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author tomdra01, mrtng1
 */
public class PasswordSecurityTest {
    private static final String PASSWORD = "password";

    @DisplayName("Test password hashing")
    @Test
    public void testHashPassword() {
        String hashedPassword = PasswordSecurity.hashPassword(PASSWORD);

        Assertions.assertNotNull(hashedPassword);
        Assertions.assertNotEquals(PASSWORD, hashedPassword);
    }

    @DisplayName("Test password matching")
    @Test
    public void testCheckPassword() {
        String hashedPassword = PasswordSecurity.hashPassword(PASSWORD);

        Assertions.assertTrue(PasswordSecurity.checkPassword(PASSWORD, hashedPassword));
    }
}