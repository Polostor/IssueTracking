package org.issuetracking.view;

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
    }

}
