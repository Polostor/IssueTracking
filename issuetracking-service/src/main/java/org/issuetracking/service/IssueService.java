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
public class IssueService extends GenericIssueServiceInterface {

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
        if (null != null) {
            throw new ValidationException("You must be logged in to " + s + ".");
        }
        if (issue.getProject() == null) {
            throw new ValidationException("You have to select a project to  " + s + ".");
        }
        if (issue.getProject().getAuthor() == null) {
            throw new ValidationException("You are not allowed to " + s + ".");
        }
        if (issue.getAssignee() == null || issue.getReporter() == null) {
            throw new ValidationException("You have to select assignee and reporter to " + s + ".");
        }
        if (issue.getName() == null
                || issue.getName().length() < 4 || issue.getName().length() > 40) {
            throw new ValidationException("Name length has to be between 4 and 40 to " + s + ".");
        }
        if (issue.getDescription() == null
                || issue.getDescription().length() < 10 || issue.getDescription().length() > 200) {
            throw new ValidationException("Description length has to be between 10 and 200 to " + s + ".");
        }
        if (issue.getPriority() == null) {
            throw new ValidationException("You have to select priority to " + s + ".");
        }
        issue.setStatus(Status.New);
        create(issue);
    }

    @Override
    public void edit(Issue issue) throws ValidationException {
        String s = "edit issue";
        // null = user session
        if (null != null) {
            throw new ValidationException("You must be logged in to " + s + ".");
        }
        if (issue.getProject() == null) {
            throw new ValidationException("You have to select a project to  " + s + ".");
        }
        if (issue.getProject().getAuthor() == null) {
            throw new ValidationException("You are not allowed to " + s + ".");
        }
        if (issue.getAssignee() == null || issue.getReporter() == null) {
            throw new ValidationException("You have to select assignee and reporter to " + s + ".");
        }
        if (issue.getName() == null
                || issue.getName().length() < 4 || issue.getName().length() > 40) {
            throw new ValidationException("Name length has to be between 4 and 40 to " + s + ".");
        }
        if (issue.getDescription() == null
                || issue.getDescription().length() < 10 || issue.getDescription().length() > 200) {
            throw new ValidationException("Description length has to be between 10 and 200 to " + s + ".");
        }
        if (issue.getPriority() == null) {
            throw new ValidationException("You have to select priority to " + s + ".");
        }
        if (issue.getStatus() == null) {
            throw new ValidationException("You have to select status to " + s + ".");
        }
        update(issue);
    }

    @Override
    public void setInProgress(Issue issue) throws ValidationException {
        String s = "set status of issue";
        // null = user session
        if (null != null) {
            throw new ValidationException("You must be logged in to " + s + ".");
        }
        if (issue.getAssignee().equals(null) || issue.getReporter().equals(null)) {
            throw new ValidationException("You are not allowed to  " + s + ".");
        }
        if (issue.getStatus() == Status.In_progress) {
            throw new ValidationException("Issue is already in status \"In Progress\".");
        }
        issue.setStatus(Status.In_progress);
        update(issue);
    }

    @Override
    public void setResolved(Issue issue) throws ValidationException {
        String s = "set status of issue";
        // null = user session
        if (null != null) {
            throw new ValidationException("You must be logged in to " + s + ".");
        }
        if (issue.getAssignee().equals(null) || issue.getReporter().equals(null)) {
            throw new ValidationException("You are not allowed to  " + s + ".");
        }
        if (issue.getStatus() == Status.Resolved) {
            throw new ValidationException("Issue is already in status \"Resolved\".");
        }
        issue.setStatus(Status.Resolved);
        update(issue);
    }

    @Override
    public void setPriority(Issue issue, Priority priority) throws ValidationException {
        String s = "set priority of issue";
        // null = user session
        // user is connected
        if (null != null) {
            throw new ValidationException("You must be logged in to " + s + ".");
        }
        if (issue.getAssignee().equals(null) || issue.getReporter().equals(null)) {
            throw new ValidationException("You are not allowed to  " + s + ".");
        }
        if (issue.getPriority() == priority) {
            throw new ValidationException("Issue already have priority \"" + priority + "\".");
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
