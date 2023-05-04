package dk.easv.bll.logic;

import dk.easv.be.Project;
import dk.easv.be.roles.Admin;
import dk.easv.be.roles.Technician;
import dk.easv.bll.exception.DatabaseException;
import dk.easv.dal.ProjectDAO;

import java.sql.SQLException;
import java.util.List;

public class ProjectLogic {
    ProjectDAO projectDAO = new ProjectDAO();

    public List<Project> readAllProjects() throws DatabaseException {
        return projectDAO.readAllProjects();
    }

    public Project createProject(Project project) throws SQLException {
        return projectDAO.createProject(project);
    }
}
