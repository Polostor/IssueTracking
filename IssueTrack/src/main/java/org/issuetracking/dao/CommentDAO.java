package org.issuetracking.dao;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

=======
import java.util.List;
>>>>>>> origin/master
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
    public Comment find(long id) {
        Comment comm = em.find(Comment.class, id);
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
}
