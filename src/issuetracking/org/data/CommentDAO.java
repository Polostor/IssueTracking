package issuetracking.org.data;

import static issuetracking.org.Start.getSession;
import issuetracking.org.model.Comment;
import java.util.List;

/**
 *
 * @author peta
 */
public class CommentDAO implements GenericDAO<Comment> {

    @Override
    public long create(Comment obj) {
        return (long) getSession().save(obj);
    }

    @Override
    public Comment find(int id) {
        return (Comment) getSession().get("int", id);
    }

    @Override
    public List<Comment> findAll() {
        return getSession().createCriteria(Comment.class).list();
    }

    @Override
    public void update(Comment obj) {
       getSession().update(obj);
    }

}
