package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.issuetracking.model.Comment;
import org.issuetracking.service.CommentService;
import org.issuetracking.service.ValidationException;

@RequestScoped
public class CommentBean {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Inject
    private CommentService gServ;

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
            navigate("comments");
        }
        try {
            comment = gServ.view(id);
        } catch (ValidationException ex) {
            showException(ex);
        }
        if (comment == null || comment.getAuthor() == null) {
            navigate("comments");
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
            gServ.add(comment);
            return "/comments.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public String updateComment() {
        try {
            gServ.edit(comment);
            return "/comments.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public void init() {
        if (id < 1) {
            navigate("comments");
        }
        try {
            comment = gServ.view(id);
        } catch (ValidationException ex) {
            showException(ex);
        }
        if (comment == null || comment.getAuthor() == null) {
            navigate("comments");
        }
    }
    
    private void navigate(String where){
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation(where);        
    }
    
    private void showException(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validation Error", ex.toString()));    
    }

}
