package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
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
        issue = gServ.getObjById(id);
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
        return "list.xhtml";
    }

    public String updateIssue() {
        gServ.update(issue);
        return "list.xhtml";
    }

    public String init() {
        System.out.println("id - " + id);
        if (id == 0) {
            return "list.xhtml";
        }
        issue = gServ.getObjById(id);
        return "";
    }

}
