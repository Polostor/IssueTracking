package org.issuetracking.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import javax.ejb.EJB;
import org.issuetracking.model.Issue;
import org.issuetracking.service.base.IssueService;

@ManagedBean
@SessionScoped
public class IssueBean implements Serializable {

    private Issue issue;
    
    @EJB
    public IssueService issues;

    private static final long serialVersionUID = 1L;

    private final String welcome = "Welcome at the IssueTracking webpage";

    public String getWelcome() {
        return welcome;
    }
    
    public void create() throws Exception{     
        issues.create(issue);
    }

}
