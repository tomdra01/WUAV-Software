package be;

// imports
import dk.easv.be.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

// java imports
import java.time.LocalDateTime;

/**
 *
 * @author tomdra01, mrtng1
 */
public class LogTest {
    private Log log;

    @Before
    public void setUp() {
        LocalDateTime actionTime = LocalDateTime.now();
        log = new Log(1, "testLogAction", actionTime, "testUsername");
    }

    @DisplayName("Test log getter")
    @Test
    public void testLogGetter() {
        Assertions.assertEquals(1, log.getId());
        Assertions.assertEquals("testLogAction", log.getLogAction());
        // Add assertions for other getter methods
        Assertions.assertEquals("testUsername", log.getUsername());
    }

    @DisplayName("Test log setter")
    @Test
    public void testLogSetter() {
        log.setId(2);
        log.setLogAction("newLogAction");

        Assertions.assertEquals(2, log.getId());
        Assertions.assertEquals("newLogAction", log.getLogAction());
    }

    @DisplayName("Test log with null values")
    @Test
    public void testLogWithNullValues() {
        log.setId(0);
        log.setLogAction(null);

        Assertions.assertEquals(0, log.getId());
        Assertions.assertNull(log.getLogAction());
    }

    @DisplayName("Test log to string")
    @Test
    public void testLogToString() {
        LocalDateTime actionTime = LocalDateTime.now();
        Log log = new Log(1, "testLogAction", actionTime, "testUsername");
        String expectedToString = "[" + actionTime + "]" + " /testLogAction /By user:testUsername";
        Assertions.assertEquals(expectedToString, log.toString());
    }
}