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
        
        Validator.isLoggedIn(s);
        Validator.isNotNull(comment.getIssue(), "issue", s);
        Validator.isOneOfAllowedUsers(comment.getIssue().getAssignee(), comment.getIssue().getReporter(), s);
        Validator.isNotNull(comment.getAuthor(), "author", s);
        Validator.isBetween(comment.getName(), 4, 40, "Name", s);
        Validator.isBetween(comment.getComment(), 10, 200, "Comment", s);
        
        comment.setCommentdate(new Date());
        create(comment);
    }

    @Override
    public void edit(Comment comment) throws ValidationException {
        String s = "edit comment";
        
        Validator.isLoggedIn(s);
        Validator.isNotNull(comment.getIssue(), "issue", s);
        Validator.isOneOfAllowedUsers(comment.getIssue().getAssignee(), comment.getIssue().getReporter(), s);
        Validator.isNotNull(comment.getAuthor(), "author", s);
        Validator.isBetween(comment.getName(), 4, 40, "Name", s);
        Validator.isBetween(comment.getComment(), 10, 200, "Comment", s);
        Validator.isDateEarlier(comment.getCommentdate(), s);
        
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
