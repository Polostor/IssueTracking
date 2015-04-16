package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;
import org.issuetracking.model.Priority;
import org.issuetracking.model.Status;

import org.issuetracking.service.generic.GenericIssueServiceInterface;

@Stateless
public class IssueService extends GenericIssueServiceInterface{

    @Inject
    protected IssueDAO gDAO;

    @Override
    protected IssueDAO getDAO() {
        return gDAO;
    }

    @Override
    public void add(Issue issue) throws ValidationException {
        String s = "add issue";
        
        Validator.isLoggedIn(s);
        Validator.isNotNull(issue.getProject(), "project", s);
        Validator.isAllowedUser(issue.getAssignee(), s);        
        Validator.isNotNull(issue.getAssignee(), "assignee", s);
        Validator.isNotNull(issue.getReporter(), "reporter", s);
        Validator.isBetween(issue.getName(), 4, 40, "Name", s);
        Validator.isBetween(issue.getDescription(), 10, 200, "Description", s);
        Validator.isNotNull(issue.getPriority(), "priority", s);
        
        issue.setStatus(Status.New);      
        create(issue);
    }

    @Override
    public void edit(Issue issue) throws ValidationException {
        String s = "edit issue";
        
        Validator.isLoggedIn(s);
        Validator.isNotNull(issue.getProject(), "project", s);
        Validator.isAllowedUser(issue.getAssignee(), s);        
        Validator.isNotNull(issue.getAssignee(), "assignee", s);
        Validator.isNotNull(issue.getReporter(), "reporter", s);
        Validator.isBetween(issue.getName(), 4, 40, "Name", s);
        Validator.isBetween(issue.getDescription(), 10, 200, "Description", s);
        Validator.isNotNull(issue.getPriority(), "priority", s);
        Validator.isNotNull(issue.getStatus(), "status", s);
         
        update(issue);
    }

    @Override
    public void setInProgress(Issue issue) throws ValidationException {
        String s = "set status of issue";
        
        Validator.isLoggedIn(s);
        Validator.isOneOfAllowedUsers(issue.getAssignee(), issue.getReporter(), s);
        Validator.isNotSame(issue.getStatus(), Status.In_progress, "Status");
        
        issue.setStatus(Status.In_progress);
        update(issue);
    }

    @Override
    public void setResolved(Issue issue) throws ValidationException {
        String s = "set status of issue";
        
        Validator.isLoggedIn(s);
        Validator.isOneOfAllowedUsers(issue.getAssignee(), issue.getReporter(), s);
        Validator.isNotSame(issue.getStatus(), Status.Resolved, "Status");
        
        issue.setStatus(Status.Resolved);
        update(issue);
    }

    @Override
    public void setPriority(Issue issue, Priority priority) throws ValidationException {
        String s = "set priority of issue";
        
        Validator.isLoggedIn(s);
        Validator.isOneOfAllowedUsers(issue.getAssignee(), issue.getReporter(), s);
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
