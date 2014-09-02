package issuetracking.org.service.generic;

import issuetracking.org.dao.CommentDAO;
import issuetracking.org.model.Comment;

public class CommentService extends GenericService<Comment> {

    CommentDAO gDAO;

    public CommentService() {
        this.gDAO = new CommentDAO();
    }
}
