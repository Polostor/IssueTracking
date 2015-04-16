package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.Issue;
import org.issuetracking.service.IssueService;
import org.issuetracking.service.ValidationException;

@Named
@RequestScoped
public class IssueBean {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Inject
    private IssueService gServ;

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
            gServ.add(issue);
            return "/issues.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public String updateIssue() {
        try {
            gServ.edit(issue);
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
    
    private void navigate(String where){
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation(where);        
    }
    
    private void showException(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validation Error - " + ex.getMessage(), ex.toString()));    
    }

}
