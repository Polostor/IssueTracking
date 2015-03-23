package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.Issue;
import org.issuetracking.service.IssueService;

@Named(value = "issueBean")
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
        return gServ.getAllObjs();
    }

    public Issue getIssueById() {
        if (id < 1) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation("new");
            return null;
        }
        issue = gServ.getObj(id);
        if(issue.getDescription() == null && issue.getIssuedate() == null){
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation("new");
            return null;
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
        gServ.create(issue);
        return "/issues.xhtml?faces-redirect=true";
    }

    public String updateIssue() {
        gServ.update(issue);
        return "/issues.xhtml?faces-redirect=true";
    }

    public String init() {
        if (id < 1) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation("issue/new");
            return "new.xhtml";
        }
        issue = gServ.getObj(id);
        if(issue.getDescription() == null && issue.getIssuedate() == null){
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation("issue/new");
            return "new.xhtml";
        }
        return "";
    }

}