package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.model.Project;
import org.issuetracking.dao.ProjectDAO;

import org.issuetracking.service.generic.GenericProjectServiceInterface;
import org.issuetracking.view.iface.PrincipalBeanInterface;

@Stateless
public class ProjectService extends GenericProjectServiceInterface {

    @Inject
    protected ProjectDAO gDAO;

    @Override
    protected ProjectDAO getDAO() {
        return gDAO;
    }

    @Override
    public void add(Project project, PrincipalBeanInterface pb) throws ValidationException {
        Validator.isLoggedIn(pb);
        Validator.isAllowedUser(project.getAuthor(), pb);
        BusinessValidator.validate(project);

        create(project);
    }

    @Override
    public void edit(Project project, PrincipalBeanInterface pb) throws ValidationException {
        Validator.isLoggedIn(pb);
        Validator.isAllowedUser(project.getAuthor(), pb);
        BusinessValidator.validate(project);

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
