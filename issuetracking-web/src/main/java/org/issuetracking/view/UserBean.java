package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.User;
import org.issuetracking.service.UserService;
import org.issuetracking.service.ValidationException;
import org.issuetracking.view.generic.BasicBean;

@Named
@RequestScoped
public class UserBean extends BasicBean {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Inject
    private UserService gServ;
    
    @Inject
    private PrincipalBean pb;

    private User user = new User();

    public List<User> getUsers() {
        try {
            return gServ.viewAll();
        } catch (ValidationException ex) {
            showException(ex);
            return null;
        }
    }

    public User getUserById() {
        if (id < 1) {
            navigate("/users.xhtml");
        }
        try {
            user = gServ.view(id);
        } catch (ValidationException ex) {
            showException(ex);
        }
        if (user == null || user.getEmail() == null) {
            navigate("/users.xhtml");
        }
        return user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String saveUser() {
        try {
            gServ.add(user, pb);
            return "/users.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public String updateUser() {
        try {
            gServ.edit(user, pb);
            return "/users.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public void init() {
        if (id < 1) {
            navigate("/users.xhtml");
        }
        try {
            user = gServ.view(id);
        } catch (ValidationException ex) {
            showException(ex);
        }
        if (user == null || user.getId() == null) {
            navigate("/users.xhtml");
        }
    }

}
