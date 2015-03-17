package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.Project;
import org.issuetracking.service.ProjectService;

@Named(value = "projectBean")
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
        return gServ.getAllObjs();
    }

    public Project getProjectById() {
        project = gServ.getObj(id);
        return project;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String saveProject() {
        gServ.create(project);
        return "list.xhtml";
    }

    public String updateProject() {
        gServ.update(project);
        return "list.xhtml";
    }

    public String init() {
        System.out.println("id - " + id);
        if (id == 0) {
            return "list.xhtml";
        }
        project = gServ.getObj(id);
        return "";
    }

}