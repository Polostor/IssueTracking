package org.issuetracking.service;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.CommentDAO;
import org.issuetracking.model.Comment;

import org.issuetracking.service.generic.GenericCommentServiceInterface;

@Stateless
public class CommentService extends GenericCommentServiceInterface {

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
        if (null != null) {
            throw new ValidationException("You must be logged in to " + s + ".");
        }
        if (comment.getIssue() == null) {
            throw new ValidationException("You have to select issue to  " + s + ".");
        }
        if (comment.getIssue().getAssignee() == null || comment.getIssue().getReporter() == null) {
            throw new ValidationException("You are not allowed to " + s + ".");
        }
        if (comment.getAuthor() == null) {
            throw new ValidationException("You have to select author to  " + s + ".");
        }
        if (comment.getName() == null
                || comment.getName().length() < 4 || comment.getName().length() > 40) {
            throw new ValidationException("Name length has to be between 4 and 40 to " + s + ".");
        }
        if (comment.getComment() == null
                || comment.getComment().length() < 10 || comment.getComment().length() > 200) {
            throw new ValidationException("Comment length has to be between 10 and 200 to " + s + ".");
        }
        comment.setCommentdate(new Date());
        create(comment);
    }

    @Override
    public void edit(Comment comment) throws ValidationException {
        String s = "edit comment";
        // null = user session
        if (null != null) {
            throw new ValidationException("You must be logged in to " + s + ".");
        }
        if (comment.getIssue() == null) {
            throw new ValidationException("You have to select issue to  " + s + ".");
        }
        if (comment.getIssue().getAssignee() == null || comment.getIssue().getReporter() == null) {
            throw new ValidationException("You are not allowed to " + s + ".");
        }
        if (comment.getAuthor() == null) {
            throw new ValidationException("You have to select author to  " + s + ".");
        }
        if (comment.getName() == null
                || comment.getName().length() < 4 || comment.getName().length() > 40) {
            throw new ValidationException("Name length has to be between 4 and 40 to " + s + ".");
        }
        if (comment.getComment() == null
                || comment.getComment().length() < 10 || comment.getComment().length() > 200) {
            throw new ValidationException("Comment length has to be between 10 and 200 to " + s + ".");
        }
        if (comment.getComment() == null
                || comment.getComment().length() < 10 || comment.getComment().length() > 200) {
            throw new ValidationException("Comment length has to be between 10 and 200 to " + s + ".");
        }        
        if (comment.getCommentdate() == null || comment.getCommentdate().before(new Date())) {
            throw new ValidationException("You have to set date in past to " + s + ".");
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
