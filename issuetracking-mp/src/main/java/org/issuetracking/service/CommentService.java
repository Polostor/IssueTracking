package org.issuetracking.service;

import org.issuetracking.service.annotations.NotNull;
import org.issuetracking.service.annotations.LoggedIn;
import org.issuetracking.service.annotations.DateEarlier;
import org.issuetracking.service.annotations.Length;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.issuetracking.dao.CommentDAO;
import org.issuetracking.model.Comment;
import org.issuetracking.service.annotations.OneOfAllowedUsers;

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
    @Interceptors(SecurityIntercept.class)
    @LoggedIn
    @NotNull(object = "getIssue", input = "issue")
    @OneOfAllowedUsers(id1Method = "getIssue().getAssignee", 
                id2Method = "getIssue().getReporter")
    @NotNull(object = "getAuthor", input = "author")
    @Length(min = 4, max = 40, param1 = "", input = "Name") // param1 is a comment.getName().length()
    @Length(min = 10, max = 200, param1 = "", input = "Comment") // param1 is a comment.getComment().length()
    public void add(Comment comment, PrincipalBeanInterface pb) throws ValidationException {
        comment.setCommentdate(new Date());
        create(comment);
    }

    @Override
    @Interceptors(SecurityIntercept.class)
    @LoggedIn
    @NotNull(object = "getIssue", input = "issue")
    @OneOfAllowedUsers(id1Method = "getIssue().getAssignee", 
                id2Method = "getIssue().getReporter")
    @NotNull(object = "getAuthor", input = "author")
    @Length(min = 4, max = 40, param1 = "", input = "Name") // param1 is a comment.getName().length()
    @Length(min = 10, max = 200, param1 = "", input = "Comment") // param1 is a comment.getComment().length()
    @DateEarlier(date = ".getCommentdate") // date is a comment.getCommentdate()
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
