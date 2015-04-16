package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.Project;
import org.issuetracking.service.ProjectService;
import org.issuetracking.service.ValidationException;

@Named
@RequestScoped
public class ProjectBean {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Inject
    private ProjectService gServ;

    private Project project = new Project();

    public List<Project> getProjects() {
        try {
            return gServ.viewAll();
        } catch (ValidationException ex) {
            showException(ex);
            return null;
        }
    }

    public Project getProjectById() {
        if (id < 1) {
            navigate("/projects.xhtml");
        }
        try {
            project = gServ.view(id);
        } catch (ValidationException ex) {
            showException(ex);
        }
        if (project == null || project.getId() == null) {
            navigate("/projects.xhtml");
        }
        return project;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String saveProject() {
        try {
            gServ.add(project);
            return "/projects.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public String updateProject() {
        try {
            gServ.edit(project);
            return "/projects.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public void init() {
        if (id < 1) {
            navigate("/projects.xhtml");
        }
        try {
            project = gServ.view(id);
        } catch (ValidationException ex) {
            showException(ex);
        }
        if (project == null || project.getId() == null) {
            navigate("/projects.xhtml");
        }
    }

    private void navigate(String where) {
        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

        nav.performNavigation(where);
    }
    
    private void showException(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validation Error - " + ex.getMessage(), ex.toString()));    
    }

}
