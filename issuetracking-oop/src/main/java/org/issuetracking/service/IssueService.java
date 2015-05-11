package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;
import org.issuetracking.model.Priority;
import org.issuetracking.model.Status;

import org.issuetracking.service.generic.GenericIssueServiceInterface;
import org.issuetracking.view.iface.PrincipalBeanInterface;

@Stateless
public class IssueService extends GenericIssueServiceInterface{

    @Inject
    protected IssueDAO gDAO;

    @Override
    protected IssueDAO getDAO() {
        return gDAO;
    }

    @Override
    public void add(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        Validator.isLoggedIn(pb);
        BusinessValidator.validate(issue);
        Validator.isAllowedUser(issue.getAssignee(), pb);   
        
        issue.setStatus(Status.New);      
        create(issue);
    }

    @Override
    public void edit(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        Validator.isLoggedIn(pb);
        BusinessValidator.validate(issue);
        Validator.isAllowedUser(issue.getAssignee(), pb);   
        Validator.isNotNull(issue.getStatus(), "status");
         
        update(issue);
    }

    @Override
    public void setInProgress(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        Validator.isLoggedIn(pb);
        Validator.isOneOfAllowedUsers(issue.getAssignee(), issue.getReporter(), pb);
        Validator.isNotSame(issue.getStatus(), Status.In_progress, "Status");
        
        issue.setStatus(Status.In_progress);
        update(issue);
    }

    @Override
    public void setResolved(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        Validator.isLoggedIn(pb);
        Validator.isOneOfAllowedUsers(issue.getAssignee(), issue.getReporter(), pb);
        Validator.isNotSame(issue.getStatus(), Status.Resolved, "Status");
        
        issue.setStatus(Status.Resolved);
        update(issue);
    }

    @Override
    public void setPriority(Issue issue, PrincipalBeanInterface pb, Priority priority) throws ValidationException {
        Validator.isLoggedIn(pb);
        Validator.isOneOfAllowedUsers(issue.getAssignee(), issue.getReporter(), pb);
        Validator.isNotSame(issue.getPriority(), priority, "Priority");
        
        issue.setPriority(priority);
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
