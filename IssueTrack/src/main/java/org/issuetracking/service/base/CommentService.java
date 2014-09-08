package org.issuetracking.service.base;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.issuetracking.dao.CommentDAO;
import org.issuetracking.model.Comment;

@ManagedBean
@ViewScoped
public class CommentService extends GenericService<Comment> implements Serializable {

    @EJB
    CommentDAO gDAO;

    @Override
    public boolean create(Comment obj) throws Exception {
        gDAO.create(obj);
        // TODO change as it is updted or not
        return true;
    }

    @Override
    public Comment getObj(int id) throws Exception {
        return (Comment) gDAO.find(id);
    }

    @Override
    public List<Comment> getAll() throws Exception {
        return gDAO.findAll();
    }

    @Override
    public boolean update(Comment o) throws Exception {
        gDAO.update(o);
        // TODO change as it is updted or not
        return true;
    }
}
