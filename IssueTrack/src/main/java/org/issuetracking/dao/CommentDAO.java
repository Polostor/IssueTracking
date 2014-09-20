package org.issuetracking.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.issuetracking.model.Comment;

/**
 *
 * @author peta
 */
@Stateless
public class CommentDAO extends GenericDAO<Comment> {

    @Override
    public Comment create(Comment obj) {
        em.persist(obj);
        return obj;
    }

    @Override
    public Comment find(long id) {
        Query query = em.createQuery("SELECT c FROM Comment c WHERE c.id = :id");
        query.setParameter("id", id);
        Comment com = (Comment)query.getSingleResult();
        if (com == null) {
            com = new Comment();
        }
        return com;
    }

    @Override
    public List<Comment> findAll() {
        Query query = em.createQuery("SELECT c FROM Comment c ORDER BY c.id DESC");
        List<Comment> entries = query.getResultList();
        if (entries == null) {
            entries = new ArrayList<Comment>();
        }
        return entries;
    }

    @Override
    public Comment update(Comment obj) {
        em.merge(obj);
        return obj;
    }

}
