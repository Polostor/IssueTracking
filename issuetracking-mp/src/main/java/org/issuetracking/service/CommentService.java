package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.CommentDAO;
import org.issuetracking.model.Comment;

import org.issuetracking.service.generic.GenericService;
import org.issuetracking.service.generic.GenericCommentServiceInterface;

@Stateless
public class CommentService extends GenericService<Comment, CommentDAO> implements GenericCommentServiceInterface {

    @Inject
    protected CommentDAO gDAO;

    @Override
    protected CommentDAO getDAO() {
        return gDAO;
    }

    @Override
    public void add(Comment comment) throws ValidationException {
        String s = "add comment";
        // null = user session
        // user is connected
        if (null != null){
            throw new ValidationException("You must be logged in to " + s +".");
        }
        if (comment.getIssue().getAssignee().equals(null) || comment.getIssue().getReporter().equals(null)) {
            throw new ValidationException("You are not allowed to " + s +".");
        }
        create(comment);
    }

    @Override
    public void edit(Comment comment) throws ValidationException {
        String s = "edit comment";
        // null = user session
        // user is connected
        if (null != null){
            throw new ValidationException("You must be logged in to " + s +".");
        }
        if (comment.getIssue().getAssignee().equals(null) || comment.getIssue().getReporter().equals(null)) {
            throw new ValidationException("You are not allowed to " + s +".");
        }
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
