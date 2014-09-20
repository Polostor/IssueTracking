package org.issuetracking.service;

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
    public void create(Comment obj) {
        gDAO.create(obj);
    }

    @Override
    public void update(Comment obj) {
        gDAO.update(obj);
    }
    
    public Comment getObjById(long id) {
        return gDAO.find(id);
    }

    @Override
    public List<Comment> getAllObjs() {
        return ((CommentDAO) gDAO).findAll();
    }
}
