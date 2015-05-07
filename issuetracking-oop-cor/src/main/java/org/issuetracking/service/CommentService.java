package org.issuetracking.service;

import org.issuetracking.service.Validation.BusinessValidator;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.CommentDAO;
import org.issuetracking.model.Comment;

import org.issuetracking.service.generic.GenericCommentServiceInterface;
import org.issuetracking.view.iface.PrincipalBeanInterface;

@Stateless
public class CommentService extends GenericCommentServiceInterface {

    @Inject
    protected CommentDAO gDAO;

    @Override
    protected CommentDAO getDAO() {
        return gDAO;
    }

    @Override
    public void add(Comment comment, PrincipalBeanInterface pb) throws ValidationException {
        BusinessValidator.validate(comment, pb);
        
        comment.setCommentdate(new Date());
        create(comment);
    }

    @Override
    public void edit(Comment comment, PrincipalBeanInterface pb) throws ValidationException {
        BusinessValidator.validate(comment, pb);

        update(comment);
    }

    @Override
    public Comment view(long id) throws ValidationException {
        // this content should be available to anyone
        return getObj(id);
    }

    @Override
    public List<Comment> viewAll() throws ValidationException {
        // this content should be available to anyone
        return getAllObjs();
    }
}
