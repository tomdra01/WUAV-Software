package dk.easv.be.roles;

// imports
import dk.easv.be.User;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ProjectManager extends User {
    public ProjectManager(int id, String username, String password) {
        super(id, username, password);
    }
    public ProjectManager(String username, String password) {super(username, password);}
}
