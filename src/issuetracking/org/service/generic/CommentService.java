package issuetracking.org.service.generic;

import issuetracking.org.model.Comment;
import issuetracking.org.data.CommentDAO;
import java.util.List;

public class CommentService  implements GenericService<Comment>{
    
    CommentDAO cDAO = new CommentDAO();

    @Override
    public boolean create(Comment obj) {
        return (cDAO.create(obj) == 0) ? true : false;
    }

    @Override
    public Comment find(int id) {
        return cDAO.find(id);
    }

    @Override
    public List<Comment> findAll() {
        return cDAO.findAll();
    }

    @Override
    public boolean update(Comment obj) {
        cDAO.update(obj);
        // TODO change as it is updted or not
        return true;
    }
    
}
