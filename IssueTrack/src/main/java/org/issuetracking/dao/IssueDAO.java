package org.issuetracking.dao;

import java.util.List;
import javax.persistence.Query;
import org.issuetracking.model.Issue;

/**
 *
 * @author peta
 */
public class IssueDAO extends GenericDAO<Issue> {

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
