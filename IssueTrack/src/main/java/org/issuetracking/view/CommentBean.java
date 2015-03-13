package org.issuetracking.view;

<<<<<<< HEAD
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.Comment;
import org.issuetracking.service.CommentService;

@Named(value = "commentBean")
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
        return gServ.getAllObjs();
    }

    public Comment getCommentById() {
        comment = gServ.getObjById(id);
        return comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment Obj) {
        this.comment = Obj;
    }

    public String saveComment() {
        gServ.create(comment);
        return "list.xhtml";
    }

    public String updateComment() {
        gServ.update(comment);
        return "list.xhtml";
    }

    public String init() {
        System.out.println("id - " + id);
        if (id == 0) {
            return "list.xhtml";
        }
        comment = gServ.getObjById(id);
        return "";
=======
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import javax.ejb.EJB;
import org.issuetracking.model.Comment;
import org.issuetracking.service.base.CommentService;

@ManagedBean
@SessionScoped
public class CommentBean implements Serializable {
    
    private Comment comment;
    
    @EJB
    public CommentService comments;

    private static final long serialVersionUID = 1L;

    private final String welcome = "Welcome at the IssueTracking webpage";

    public String getWelcome() {
        return welcome;
    }
    
    public void create() throws Exception{        
        comments.create(comment);
>>>>>>> origin/master
    }

}
