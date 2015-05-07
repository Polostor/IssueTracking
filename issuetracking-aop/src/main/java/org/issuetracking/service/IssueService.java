package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.guvnor.annotation.RequiredRules;
import org.guvnor.annotation.Validate;

import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;
import org.issuetracking.model.Priority;
import org.issuetracking.model.Status;

import org.issuetracking.service.generic.GenericIssueServiceInterface;
import org.issuetracking.view.iface.PrincipalBeanInterface;

@Stateless
public class IssueService extends GenericIssueServiceInterface {

    @Inject
    protected IssueDAO gDAO;

    @Override
    protected IssueDAO getDAO() {
        return gDAO;
    }

    @Override
    @Validate
    /*@RequiredRules( "You must be logged in" )
    @RequiredRules( "You have to select a project" )
    @RequiredRules( "You are not allowed" )
    @RequiredRules( "You have to select assignee and reporter" )
    @RequiredRules( "Name length has to be between 4 and 40" )
    @RequiredRules( "Description length has to be between 10 and 200" )
    @RequiredRules( "You have to select priority" ) */
    public void add(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        issue.setStatus(Status.New);
        create(issue);
    }

    @Override
    @Validate
    /*@RequiredRules( "You must be logged in" )
    @RequiredRules( "You have to select a project" )
    @RequiredRules( "You are not allowed" )
    @RequiredRules( "You have to select assignee and reporter" )
    @RequiredRules( "Name length has to be between 4 and 40" )
    @RequiredRules( "Description length has to be between 10 and 200" )
    @RequiredRules( "You have to select priority" ) */
    @RequiredRules( "You have to select status" )
    public void edit(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        update(issue);
    }

    @Override
    @Validate
    /*@RequiredRules( "You must be logged in" )
    @RequiredRules( "You are not allowed" ) */
    @RequiredRules( "Issue is already in status 'In Progress'" )
    public void setInProgress(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        issue.setStatus(Status.In_progress);
        update(issue);
    }

    @Override
    @Validate
    /*@RequiredRules( "You must be logged in" )
    @RequiredRules( "You are not allowed" ) */
    @RequiredRules( "Issue is already in status 'Resolved'" )
    public void setResolved(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        issue.setStatus(Status.Resolved);
        update(issue);
    }

    @Override
    @Validate
    /*@RequiredRules( "You must be logged in" )
    @RequiredRules( "You are not allowed" ) */
    @RequiredRules( "Issue already have this priority" )
    public void setPriority(Issue issue, PrincipalBeanInterface pb, Priority prior)  throws ValidationException {
        issue.setPriority(prior);
        update(issue);
    }

    @Override
    public Issue view(long id) throws ValidationException {
        // this content should be available to anyone
        return getObj(id);
    }

    @Override
    public List<Issue> viewAll() throws ValidationException {
        // this content should be available to anyone
        return getAllObjs();
    }
}
