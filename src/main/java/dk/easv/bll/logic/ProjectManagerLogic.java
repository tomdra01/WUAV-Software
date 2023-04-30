package dk.easv.bll.logic;

import dk.easv.be.roles.ProjectManager;
import dk.easv.be.roles.Salesman;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.ProjectManagerDAO;
import dk.easv.dal.SalesmanDAO;

import java.sql.SQLException;
import java.util.List;

public class ProjectManagerLogic {
    ProjectManagerDAO projectManagerDAO = new ProjectManagerDAO();

    public List<ProjectManager> readAllProjectManagers() throws DatabaseException {
        return projectManagerDAO.readAllProjectManagers();
    }

    public ProjectManager createProjectManager(ProjectManager projectManager) throws SQLException {
        return projectManagerDAO.createProjectManager(projectManager);
    }
}
