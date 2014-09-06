package org.issuetracking.service.base;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.issuetracking.dao.CommentDAO;
import org.issuetracking.model.Comment;

@Stateless
public class CommentService extends GenericService<Comment> {

    @Inject
    CommentDAO gDAO;

    @Override
    public boolean createObj(Comment obj) throws Exception {
        gDAO.create(obj);
        // TODO change as it is updted or not
        return true;
    }

    @Override
    public Comment getObj(int id) throws Exception {
        return (Comment) gDAO.find(id);
    }

    @Override
    public List<Comment> getAllObjs() throws Exception {
        return gDAO.findAll();
    }

    @Override
    public boolean updateObj(Comment o) throws Exception {
        gDAO.update(o);
        // TODO change as it is updted or not
        return true;
    }

//    @Override
//    public void setUp() throws Exception {
//        gDAO = new CommentDAO();
//        super.setUp();
//    }
}
