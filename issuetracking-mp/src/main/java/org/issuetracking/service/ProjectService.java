package org.issuetracking.service;

import org.issuetracking.service.annotations.NotNull;
import org.issuetracking.service.annotations.LoggedIn;
import org.issuetracking.service.annotations.Length;
import org.issuetracking.service.annotations.AllowedUser;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.issuetracking.model.Project;
import org.issuetracking.dao.ProjectDAO;

import org.issuetracking.service.generic.GenericProjectServiceInterface;
import org.issuetracking.view.iface.PrincipalBeanInterface;

@Stateless
public class ProjectService extends GenericProjectServiceInterface{

    @Inject
    protected ProjectDAO gDAO;

    @Override
    protected ProjectDAO getDAO() {
        return gDAO;
    }

    @Override
    @Interceptors(SecurityIntercept.class)
    @LoggedIn
    @NotNull(object = "getAuthor", input = "Author")
    @AllowedUser(id1Method = "getAuthor")
    @Length(min = 4, max = 40, param1 = "", input = "Name") // param1 is a project.getName().length()
    @Length(min = 10, max = 100, param1 = "", input = "Description") 
        // param1 is a project.getDescription().length()
    public void add(Project project, PrincipalBeanInterface pb) throws ValidationException {
        create(project);
    }

    @Override
    @Interceptors(SecurityIntercept.class)
    @LoggedIn
    @NotNull(object = "getAuthor", input = "Author")
    @AllowedUser(id1Method = "getAuthor")
    @Length(min = 4, max = 40, param1 = "", input = "Name") // param1 is a project.getName().length()
    @Length(min = 10, max = 100, param1 = "", input = "Description") 
        // param1 is a project.getDescription().length()
    public void edit(Project project, PrincipalBeanInterface pb) throws ValidationException {
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
