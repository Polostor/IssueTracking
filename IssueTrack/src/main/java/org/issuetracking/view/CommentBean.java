package org.issuetracking.view;

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
        comment = gServ.getObj(id);
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
        comment = gServ.getObj(id);
        return "";
    }

}
