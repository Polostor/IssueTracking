package org.issuetracking.dao;

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
    public void create(Comment obj) {
        entityManager.persist(obj);
    }

    @Override
    public Comment find(int id) {
        Query commentQuery = entityManager.createNamedQuery("Comment.findByIdcomment", Comment.class);
        commentQuery.setParameter("idcomment", id);
        return (Comment) commentQuery.getSingleResult();
    }

    @Override
    public List<Comment> findAll() {
        Query query = entityManager.createQuery("Comment.findAll", Comment.class);
        return query.getResultList();
    }

    @Override
    public void update(Comment obj) {
        entityManager.merge(obj);
    }

}
