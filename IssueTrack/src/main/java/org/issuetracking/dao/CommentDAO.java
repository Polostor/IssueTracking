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
        final Query query = em.createQuery("SELECT b FROM Comment b WHERE b.id = :id")
                .setParameter("id", id);
        Comment comm = (Comment) query.getSingleResult();
        if (comm == null) {
            comm = new Comment();
        }
        return comm;
    }

    @Override
    public List<Comment> findAll() {
        List<Comment> entries = em.createQuery("SELECT c FROM Comment c ORDER BY c.id ASC")
                .getResultList();
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
