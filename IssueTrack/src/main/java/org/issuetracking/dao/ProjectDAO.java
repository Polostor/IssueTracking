package org.issuetracking.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.issuetracking.model.Project;


@Stateless
public class ProjectDAO extends GenericDAO<Project> {

    @Override
    public Project find(long id) {
        Project proj = em.find(Project.class, id);
        if (proj == null) {
            proj = new Project();
        }
        return proj;
    }

    @Override
    public List<Project> findAll() {
        List<Project> entries = em.createQuery("SELECT c FROM Project c ORDER BY c.id ASC")
                .getResultList();
        if (entries == null) {
            entries = new ArrayList<Project>();
        }
        return entries;
    }
}
