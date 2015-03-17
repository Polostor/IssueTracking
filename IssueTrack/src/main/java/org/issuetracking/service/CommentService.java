package org.issuetracking.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.CommentDAO;
import org.issuetracking.model.Comment;

@Stateless
public class CommentService extends GenericService<Comment, CommentDAO> {

    @Inject
    protected CommentDAO gDAO;

    @Override
    protected CommentDAO getDAO() {
        return gDAO;
    }
}
