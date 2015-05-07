package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.Issue;
import org.issuetracking.service.IssueService;
import org.issuetracking.service.ValidationException;
import org.issuetracking.view.generic.BasicBean;
    
@Named
@RequestScoped
public class IssueBean extends BasicBean {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Inject
    private IssueService gServ;
    
    @Inject
    private PrincipalBean pb;

    private Issue issue = new Issue();

    public List<Issue> getIssues() {
        try {
            return gServ.viewAll();
        } catch (ValidationException ex) {
            showException(ex);
            return null;
        }
    }

    public Issue getIssueById() {
        if (id < 1) {
            navigate("/issues.xhtml");
        }
        try {
            issue = gServ.view(id);
        } catch (ValidationException ex) {
            showException(ex);
        }
        if (issue == null || issue.getPriority()== null) {
            navigate("/issues.xhtml");
        }
        return issue;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String saveIssue() {
        try {
            gServ.add(issue, pb);
            return "/issues.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public String updateIssue() {
        try {
            gServ.edit(issue, pb);
            return "/issues.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public void init() {
        if (id < 1) {
            navigate("/issues.xhtml");
        }
        try {
            issue = gServ.view(id);
        } catch (ValidationException ex) {
            showException(ex);
        }
        if (issue == null || issue.getPriority()== null) {
            navigate("/issues.xhtml");
        }
    }
    
}
