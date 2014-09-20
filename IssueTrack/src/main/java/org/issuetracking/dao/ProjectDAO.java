package org.issuetracking.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.issuetracking.model.Project;

/**
 *
 * @author peta
 */
@Stateless
public class ProjectDAO extends GenericDAO<Project> {

    @Override
    public Project create(Project obj) {
        em.persist(obj);
        return obj;
    }

    @Override
    public Project find(long id) {
        final Query query = em.createQuery("SELECT b FROM Project b WHERE b.id = :id")
                .setParameter("id", id);
        Project proj = (Project) query.getSingleResult();
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

    @Override
    public Project update(Project obj) {
        em.merge(obj);
        return obj;
    }
}
