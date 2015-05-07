package org.issuetracking.service;

import org.issuetracking.service.annotations.NotNull;
import org.issuetracking.service.annotations.NotSame;
import org.issuetracking.service.annotations.LoggedIn;
import org.issuetracking.service.annotations.Length;
import org.issuetracking.service.annotations.AllowedUser;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;
import org.issuetracking.model.Priority;
import org.issuetracking.model.Status;
import org.issuetracking.service.annotations.OneOfAllowedUsers;

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
    @Interceptors(SecurityIntercept.class)
    @LoggedIn
    @NotNull(object = "getProject", input = "project") // object is issue.getProject()
    @AllowedUser(id1Method = "getAssignee")
    @NotNull(object = "getAssignee", input = "assignee") // object is issue.getAssignee()
    @NotNull(object = "getReporter", input = "reporter") // object is issue.getReporter()
    @Length(min = 4, max = 40, param1 = "getName", input = "Name") // param1 is a issue.getName().length()
    @Length(min = 10, max = 200, param1 = "getDescription", input = "Description")
    // param1 is a issue.getDescription().length()
    @NotNull(object = "getPriority", input = "priority")
    public void add(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        issue.setStatus(Status.New);
        create(issue);
    }

    @Override
    @Interceptors(SecurityIntercept.class)
    @LoggedIn
    @NotNull(object = "getProject", input = "project")
    @AllowedUser(id1Method = "getAssignee")
    @NotNull(object = "getAssignee", input = "assignee")
    @NotNull(object = "getReporter", input = "reporter")
    @Length(min = 4, max = 40, param1 = "getName()", input = "Name") // param1 is a issue.getName().length()
    @Length(min = 10, max = 200, param1 = "getDescription", input = "Description")
    // param1 is a issue.getDescription().length()
    @NotNull(object = "getPriority", input = "priority")
    @NotNull(object = "getStatus", input = "status")
    public void edit(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        update(issue);
    }

    @Override
    @Interceptors(SecurityIntercept.class)
    @LoggedIn
    @OneOfAllowedUsers(id1Method = "getAssignee", 
                id2Method = "getReporter")
    @NotNull(object = "getStatus", input = "status")
    @NotSame(object = 1, realObjectString = "In_progress", input = "status") // object is a issue.getStatus()
    // realObjectInt is a Status.In_progress
    public void setInProgress(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        issue.setStatus(Status.In_progress);
        update(issue);
    }

    @Override
    @Interceptors(SecurityIntercept.class)
    @LoggedIn
    @OneOfAllowedUsers(id1Method = "getAssignee", 
                id2Method = "getReporter")
    @NotNull(object = "getStatus", input = "status")
    @NotSame(object = 1, realObjectString = "Resolved", input = "status") // object is a issue.getStatus()
    // realObjectInt is a Status.Resolved
    public void setResolved(Issue issue, PrincipalBeanInterface pb) throws ValidationException {
        issue.setStatus(Status.Resolved);
        update(issue);
    }

    @Override
    @Interceptors(SecurityIntercept.class)
    @LoggedIn
    @OneOfAllowedUsers(id1Method = "getAssignee", 
                id2Method = "getReporter")
    @NotNull(object = "getPriority", input = "priority")
    @NotSame(object = 1, realObjectInt = 2, input = "priority") // object is a issue.getPriority()
    // realObjectInt is a priority
    public void setPriority(Issue issue, PrincipalBeanInterface pb, Priority priority) throws ValidationException {
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
