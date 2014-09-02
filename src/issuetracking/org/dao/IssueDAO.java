package issuetracking.org.dao;

import issuetracking.org.model.Issue;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author peta
 */
public class IssueDAO implements GenericDAO<Issue> {

    @PersistenceContext(unitName = "IssueTrackingPU", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public void create(Issue obj) {
        entityManager.persist(obj);
    }

    @Override
    public Issue find(int id) {
        Query issueQuery = entityManager.createNamedQuery("Issue.findByIdissue", Issue.class);
        issueQuery.setParameter("idissue", id);
        return (Issue) issueQuery.getSingleResult();
    }

    @Override
    public List<Issue> findAll() {
        Query query = entityManager.createQuery("Issue.findAll", Issue.class);
        return query.getResultList();
    }

    @Override
    public void update(Issue obj) {
        entityManager.merge(obj);
    }
}
