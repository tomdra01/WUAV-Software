package dal;

// imports
import dk.easv.dal.DataAccessObjects;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author tomdra01, mrtng1
 */
public class DataAccessObjectsTest {

    @DisplayName("Test enum values")
    @Test
    public void testEnumValues() {
        DataAccessObjects[] expectedValues = {DataAccessObjects.USER_DAO, DataAccessObjects.PROJECT_DAO, DataAccessObjects.CUSTOMER_DAO};
        DataAccessObjects[] actualValues = DataAccessObjects.values();

        Assertions.assertArrayEquals(expectedValues, actualValues);
    }

    @DisplayName("Test enum valueOf")
    @Test
    public void testEnumValueOf() {
        DataAccessObjects userDAO = DataAccessObjects.valueOf("USER_DAO");
        DataAccessObjects projectDAO = DataAccessObjects.valueOf("PROJECT_DAO");
        DataAccessObjects customerDAO = DataAccessObjects.valueOf("CUSTOMER_DAO");

        Assertions.assertEquals(DataAccessObjects.USER_DAO, userDAO);
        Assertions.assertEquals(DataAccessObjects.PROJECT_DAO, projectDAO);
        Assertions.assertEquals(DataAccessObjects.CUSTOMER_DAO, customerDAO);
    }
}
