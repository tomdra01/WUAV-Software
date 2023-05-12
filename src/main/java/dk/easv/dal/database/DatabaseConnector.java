package dk.easv.dal.database;

// imports
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

// java imports
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 *
 * @author tomdra01, mrtng1
 */
public class DatabaseConnector {
    private final SQLServerDataSource dataSource;
    private static final String file = "src/main/java/dk/easv/dal/database/database.properties";

    // Constructor
    public DatabaseConnector(){
        Properties properties = getConnectionDetails();
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(properties.getProperty("name"));
        dataSource.setUser(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setServerName(properties.getProperty("server"));
        dataSource.setPortNumber(Integer.parseInt(properties.getProperty("port")));
        dataSource.setTrustServerCertificate(true);
    }

    // Get Connection
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    // Properties
    public static Properties getConnectionDetails(){
        Properties properties = new Properties();

        try {
            FileInputStream sr = new FileInputStream(file);
            properties.load(sr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
