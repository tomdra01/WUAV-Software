package dal;

// static imports
import dk.easv.dal.database.DatabaseConnector;
import static org.junit.Assert.*;

// imports
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

// java imports
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author tomdra01, mrtng1
 */
public class DatabaseConnectorTest {
    private static DatabaseConnector connector;

    @BeforeClass
    public static void setUp() {
        connector = new DatabaseConnector();
    }

    @DisplayName("Test the connection")
    @Test
    public void testConnectionNotNull() throws SQLException {
        Connection connection = connector.getConnection();
        assertNotNull(connection);
        assertTrue(connection.isValid(1));
        connection.close();
    }

    @DisplayName("Test the connection details")
    @Test
    public void testConnectionDetailsLoaded() {
        assertNotNull(DatabaseConnector.getConnectionDetails());
        assertEquals("CSe2022B_e_25_WUAV", DatabaseConnector.getConnectionDetails().getProperty("name"));
        assertEquals("CSe2022B_e_25", DatabaseConnector.getConnectionDetails().getProperty("username"));
        assertEquals("CSe2022BE25#", DatabaseConnector.getConnectionDetails().getProperty("password"));
        assertEquals("10.176.111.34", DatabaseConnector.getConnectionDetails().getProperty("server"));
        assertEquals(1433, Integer.parseInt(DatabaseConnector.getConnectionDetails().getProperty("port")));
    }
}
