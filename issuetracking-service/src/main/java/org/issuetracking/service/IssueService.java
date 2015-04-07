package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;
import org.issuetracking.model.Priority;
import org.issuetracking.model.Status;

import org.issuetracking.service.generic.GenericService;
import org.issuetracking.service.generic.GenericIssueServiceInterface;

@Stateless
public class IssueService extends GenericService<Issue, IssueDAO>  implements GenericIssueServiceInterface{

    @Inject
    protected IssueDAO gDAO;

    @Override
    protected IssueDAO getDAO() {
        return gDAO;
    }

    @Override
    public void add(Issue issue) throws ValidationException {
        String s = "add issue";
        // null = user session
        // user is connected
        if (null != null){
            throw new ValidationException("You must be logged in to " + s +".");
        }
        if (issue.getProject().getAuthor().equals(null)) {
            throw new ValidationException("You are not allowed to  " + s +".");
        }
        create(issue);
    }

    @Override
    public void edit(Issue issue) throws ValidationException {
        String s = "edit issue";
        // null = user session
        // user is connected
        if (null != null){
            throw new ValidationException("You must be logged in to " + s +".");
        }
        if (issue.getAssignee().equals(null)) {
            throw new ValidationException("You are not allowed to  " + s +".");
        }
        update(issue);
    }

    @Override
    public void setInProgress(Issue issue) throws ValidationException {
        String s = "set status of issue";
        // null = user session
        // user is connected
        if (null != null){
            throw new ValidationException("You must be logged in to " + s +".");
        }
        if (issue.getAssignee().equals(null) || issue.getReporter().equals(null)) {
            throw new ValidationException("You are not allowed to  " + s +".");
        }
        issue.setStatus(Status.In_progress);
        update(issue);
    }

    @Override
    public void setResolved(Issue issue) throws ValidationException {
        String s = "set status of issue";
        // null = user session
        // user is connected
        if (null != null){
            throw new ValidationException("You must be logged in to " + s +".");
        }
        if (issue.getAssignee().equals(null) || issue.getReporter().equals(null)) {
            throw new ValidationException("You are not allowed to  " + s +".");
        }
        issue.setStatus(Status.Resolved);
        update(issue);
    }

    @Override
    public void setPriority(Issue issue, Priority priority) throws ValidationException {
        String s = "set priority of issue";
        // null = user session
        // user is connected
        if (null != null){
            throw new ValidationException("You must be logged in to " + s +".");
        }
        if (issue.getAssignee().equals(null) || issue.getReporter().equals(null)) {
            throw new ValidationException("You are not allowed to  " + s +".");
        }
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
