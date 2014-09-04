package org.issuetracking.dao;

import java.util.List;
import javax.persistence.Query;
import org.issuetracking.model.Project;

/**
 *
 * @author peta
 */
public class ProjectDAO extends GenericDAO<Project> {

    @Override
    public void create(Project obj) {
        entityManager.persist(obj);
    }

    @Override
    public Project find(int id) {
        Query projectQuery = entityManager.createNamedQuery("Project.findByIdproject", Project.class);
        projectQuery.setParameter("idproject", id);
        return (Project) projectQuery.getSingleResult();
    }

    @Override
    public List<Project> findAll() {
        Query query = entityManager.createQuery("Project.findAll", Project.class);
        return query.getResultList();
    }

    @Override
    public void update(Project obj) {
        entityManager.merge(obj);
    }
}
