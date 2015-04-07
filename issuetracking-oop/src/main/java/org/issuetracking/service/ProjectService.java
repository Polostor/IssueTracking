package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.model.Project;
import org.issuetracking.dao.ProjectDAO;

import org.issuetracking.service.generic.GenericService;
import org.issuetracking.service.generic.GenericProjectServiceInterface;

@Stateless
public class ProjectService extends GenericService<Project, ProjectDAO>  implements GenericProjectServiceInterface{

    @Inject
    protected ProjectDAO gDAO;

    @Override
    protected ProjectDAO getDAO() {
        return gDAO;
    }

    @Override
    public void add(Project project) throws ValidationException {
        String s = "create project";
        
        Validator.isLoggedIn(s);
        
        create(project);
    }

    @Override
    public void edit(Project project) throws ValidationException {
        String s = "edit project";
        
        Validator.isLoggedIn(s);
        Validator.isAllowedUser(project.getAuthor(), s);
        
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
