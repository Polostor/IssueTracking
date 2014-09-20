package org.issuetracking.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.issuetracking.model.Issue;

/**
 *
 * @author peta
 */
@Stateless
public class IssueDAO extends GenericDAO<Issue> {

    @Override
    public Issue create(Issue obj) {
        em.persist(obj);
        return obj;
    }

    @Override
    public Issue find(long id) {
        Query query = em.createQuery("SELECT c FROM Issue c WHERE c.id = :id");
        query.setParameter("id", id);
        Issue iss = (Issue)query.getSingleResult();
        if (iss == null) {
            iss = new Issue();
        }
        return iss;
    }

    @Override
    public List<Issue> findAll() {
        Query query = em.createQuery("SELECT c FROM Issue c ORDER BY c.id DESC");
        List<Issue> entries = query.getResultList();
        if (entries == null) {
            entries = new ArrayList<Issue>();
        }
        return entries;
    }

    @Override
    public Issue update(Issue obj) {
        em.merge(obj);
        return obj;
    }
}
