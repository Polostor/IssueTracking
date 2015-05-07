package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.Comment;
import org.issuetracking.service.CommentService;
import org.issuetracking.service.ValidationException;
import org.issuetracking.view.generic.BasicBean;

@Named
@RequestScoped
public class CommentBean extends BasicBean {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Inject
    private CommentService gServ;
    
    @Inject
    private PrincipalBean pb;

    private Comment comment = new Comment();

    public List<Comment> getComments() {
        try {
            return gServ.viewAll();
        } catch (ValidationException ex) {
            showException(ex);
            return null;
        }
    }

    public Comment getCommentById() {
        if (id < 1) {
            navigate("/comments.xhtml");
        }
        try {
            comment = gServ.view(id);
        } catch (ValidationException ex) {
            showException(ex);
        }
        if (comment == null || comment.getAuthor() == null) {
            navigate("/comments.xhtml");
        }
        return comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment Obj) {
        this.comment = Obj;
    }

    public String saveComment() {
        try {
            gServ.add(comment, pb);
            return "/comments.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public String updateComment() {
        try {
            gServ.edit(comment, pb);
            return "/comments.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public void init() {
        if (id < 1) {
            navigate("/comments.xhtml");
        }
        try {
            comment = gServ.view(id);
        } catch (ValidationException ex) {
            showException(ex);
        }
        if (comment == null || comment.getAuthor() == null) {
            navigate("/comments.xhtml");
        }
    }

}
