package bll.exception;

import dk.easv.bll.exception.DatabaseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseExceptionTest {

    @Test
    public void testConstructor() {
        String message = "Database error";
        Exception cause = new Exception("Root cause");

        DatabaseException exception = new DatabaseException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}