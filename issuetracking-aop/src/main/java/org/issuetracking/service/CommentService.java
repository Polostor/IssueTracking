package org.issuetracking.service;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.guvnor.annotation.RequiredRules;
import org.guvnor.annotation.Validate;

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
    @Validate
    /*@RequiredRules( "You must be logged in" )
    @RequiredRules( "You have to select issue" )
    @RequiredRules( "You are not allowed" )
    @RequiredRules( "You have to select author" )
    @RequiredRules( "Name length has to be between 4 and 40" )
    @RequiredRules( "Comment length has to be between 10 and 200" ) */
    public void add(Comment comment, PrincipalBeanInterface pb) throws ValidationException {
        comment.setCommentdate(new Date());
        create(comment);
    }

    @Override
    @Validate
    /*@RequiredRules( "You must be logged in" )
    @RequiredRules( "You have to select issue" )
    @RequiredRules( "You are not allowed" )
    @RequiredRules( "You have to select author" )
    @RequiredRules( "Name length has to be between 4 and 40" )
    @RequiredRules( "Comment length has to be between 10 and 200" )*/
    @RequiredRules( "You have to set date in past" )
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
