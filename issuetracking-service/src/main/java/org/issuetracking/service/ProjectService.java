package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.model.Project;
import org.issuetracking.dao.ProjectDAO;

import org.issuetracking.service.generic.GenericProjectServiceInterface;

@Stateless
public class ProjectService extends GenericProjectServiceInterface {

    @Inject
    protected ProjectDAO gDAO;

    @Override
    protected ProjectDAO getDAO() {
        return gDAO;
    }

    @Override
    public void add(Project project) throws ValidationException {
        String s = "create project";
        // null = user session
        if (null != null) {
            throw new ValidationException("You must be logged in to " + s + ".");
        }
        if (project.getName() == null || 
                project.getName().length() < 4 || project.getName().length() > 40) {
            throw new ValidationException("Name length has to be between 4 and 40 to " + s + ".");
        }
        if (project.getDescription()== null || 
                project.getDescription().length() < 10 || project.getDescription().length() > 100) {
            throw new ValidationException("Description length has to be between 10 and 100 to " + s + ".");
        }
        if (project.getAuthor() == null) {
            throw new ValidationException("Author cannot be null to " + s + ".");
        }
        create(project);
    }

    @Override
    public void edit(Project project) throws ValidationException {
        String s = "edit project";
        // null = user session
        // user is connected
        if (null != null) {
            throw new ValidationException("You must be logged in to " + s + ".");
        }
        if (project.getName() == null || 
                project.getName().length() < 4 || project.getName().length() > 40) {
            throw new ValidationException("Name length has to be between 4 and 40 to " + s + ".");
        }
        if (project.getDescription()== null || 
                project.getDescription().length() < 10 || project.getDescription().length() > 100) {
            throw new ValidationException("Description length has to be between 10 and 100 to " + s + ".");
        }
        if (project.getAuthor() == null) {
            throw new ValidationException("Author cannot be null to " + s + ".");
        }
        update(project);
    }

    @Override
    public Project view(long id) throws ValidationException {
        // this content should be available to anyone
        return getObj(id);
    }

    @Override
    public List<Project> viewAll() throws ValidationException {
        // this content should be available to anyone
        return getAllObjs();
    }
}
