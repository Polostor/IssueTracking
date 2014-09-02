package issuetracking.org.dao;

import javax.ejb.Stateful;
import issuetracking.org.model.Comment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author peta
 */
@Stateful
public class CommentDAO implements GenericDAO<Comment> {

    @PersistenceContext(unitName = "IssueTrackingPU", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

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
