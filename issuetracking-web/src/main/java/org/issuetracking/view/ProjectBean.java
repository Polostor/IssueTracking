package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.Project;
import org.issuetracking.service.ProjectService;
import org.issuetracking.service.ValidationException;
import org.issuetracking.view.generic.BasicBean;

@Named
@RequestScoped
public class ProjectBean extends BasicBean {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Inject
    private ProjectService gServ;
    
    @Inject
    private PrincipalBean pb;

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
            gServ.add(project, pb);
            return "/projects.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public String updateProject() {
        try {
            gServ.edit(project, pb);
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

}
