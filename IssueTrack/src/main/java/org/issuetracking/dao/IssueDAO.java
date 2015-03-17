package org.issuetracking.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.issuetracking.model.Issue;

@Stateless
public class IssueDAO extends GenericDAO<Issue> {

    @Override
    public Issue find(long id) {
        Issue iss = em.find(Issue.class, id);
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
}
