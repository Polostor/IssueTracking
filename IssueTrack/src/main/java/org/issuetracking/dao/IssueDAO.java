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
        final Query query = em.createQuery("SELECT b FROM Issue b WHERE b.id = :id")
                .setParameter("id", id);
        Issue iss = (Issue) query.getSingleResult();
        if (iss == null) {
            iss = new Issue();
        }
        return iss;
    }

    @Override
    public List<Issue> findAll() {
        List<Issue> entries = em.createQuery("SELECT c FROM Issue c ORDER BY c.id ASC")
                .getResultList();
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
