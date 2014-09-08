package org.issuetracking.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import javax.ejb.EJB;
import org.issuetracking.model.Project;
import org.issuetracking.service.base.ProjectService;

@ManagedBean
@SessionScoped
public class ProjectBean implements Serializable {

    private Project project;
    
    @EJB
    public ProjectService projects;

    private static final long serialVersionUID = 1L;

    private final String welcome = "Welcome at the IssueTracking webpage";

    public String getWelcome() {
        return welcome;
    }
    
    public void create() throws Exception{     
        projects.create(project);
    }

}
