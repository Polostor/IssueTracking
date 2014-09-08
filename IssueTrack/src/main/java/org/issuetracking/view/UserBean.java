package org.issuetracking.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.issuetracking.model.User;
import org.issuetracking.service.base.UserService;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
    
    private User user;

    @EJB
    UserService users;

    private static final long serialVersionUID = 1L;

    private final String welcome = "Welcome at the IssueTracking webpage";

    public String getWelcome() {
        return welcome;
    }
    
    public void create() throws Exception{         
        users.create(user);
    }

}
