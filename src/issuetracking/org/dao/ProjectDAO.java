package issuetracking.org.dao;

import issuetracking.org.model.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author peta
 */
public class ProjectDAO implements GenericDAO<Project> {

    @PersistenceContext(unitName = "IssueTrackingPU", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

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
