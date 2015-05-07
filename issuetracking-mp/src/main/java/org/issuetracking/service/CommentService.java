package org.issuetracking.service;

import org.issuetracking.service.annotations.NotNull;
import org.issuetracking.service.annotations.LoggedIn;
import org.issuetracking.service.annotations.DateEarlier;
import org.issuetracking.service.annotations.Length;
import org.issuetracking.service.annotations.AllowedUser;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.issuetracking.dao.CommentDAO;
import org.issuetracking.model.Comment;

import org.issuetracking.service.generic.GenericCommentServiceInterface;
import org.issuetracking.view.iface.PrincipalBeanInterface;

@Stateless
@Interceptors(SecurityIntercept.class)
public class CommentService extends GenericCommentServiceInterface {

    @Inject
    protected CommentDAO gDAO;

    @Override
    protected CommentDAO getDAO() {
        return gDAO;
    }

    @Override
    @LoggedIn
    @NotNull(object = "getIssue()", input = "issue")
    @AllowedUser(id1Method = "getIssue().getAssignee().getId()", 
                id2Method = "getIssue().getReporter().getId()")
    @NotNull(object = "getAuthor()", input = "author")
    @Length(min = 4, max = 40, param1 = "", input = "Name") // param1 is a comment.getName().length()
    @Length(min = 10, max = 200, param1 = "", input = "Comment") // param1 is a comment.getComment().length()
    public void add(Comment comment, PrincipalBeanInterface pb) throws ValidationException {
        comment.setCommentdate(new Date());
        create(comment);
    }

    @Override
    @LoggedIn
    @NotNull(object = "getIssue()", input = "issue")
    @AllowedUser(id1Method = "getIssue().getAssignee().getId()", 
                id2Method = "getIssue().getReporter().getId()")
    @NotNull(object = "getAuthor()", input = "author")
    @Length(min = 4, max = 40, param1 = "", input = "Name") // param1 is a comment.getName().length()
    @Length(min = 10, max = 200, param1 = "", input = "Comment") // param1 is a comment.getComment().length()
    @DateEarlier(date = ".getCommentdate()") // date is a comment.getCommentdate()
    public void edit(Comment comment, PrincipalBeanInterface pb) throws ValidationException {
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
