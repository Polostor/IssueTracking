package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.guvnor.annotation.RequiredRules;
import org.guvnor.annotation.Validate;

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
    @Validate
    /*@RequiredRules( "You must be logged in" )
    @RequiredRules( "Name length has to be between 4 and 40" )
    @RequiredRules( "Description length has to be between 10 and 100" )
    @RequiredRules( "Author cannot be null" )*/
    public void add(Project project, PrincipalBeanInterface pb) throws ValidationException {
        create(project);
    }

    @Override
    @Validate
    /*@RequiredRules( "You must be logged in" )
    @RequiredRules( "Name length has to be between 4 and 40" )
    @RequiredRules( "Description length has to be between 10 and 100" )
    @RequiredRules( "Author cannot be null" )*/
    @RequiredRules( "You are not allowed" )
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
