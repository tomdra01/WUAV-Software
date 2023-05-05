package dk.easv.bll.exception;

// imports
import java.sql.SQLException;

/**
 *
 * @author tomdra01, mrtng1
 */
public class DatabaseException extends SQLException {
    public DatabaseException(String message, Exception e) {
        super(message, e);
    }

    public DatabaseException(String message) {
        super(message);
    }
}
