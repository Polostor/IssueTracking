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
        Query query = em.createQuery("SELECT c FROM Project c WHERE c.id = :id");
        query.setParameter("id", id);
        Project p = (Project)query.getSingleResult();
        if (p == null) {
            p = new Project();
        }
        return p;
    }

    @Override
    public List<Project> findAll() {
        Query query = em.createQuery("SELECT c FROM Project c ORDER BY c.id DESC");
        List<Project> entries = query.getResultList();
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
