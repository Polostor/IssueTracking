package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.User;
import org.issuetracking.service.UserService;
import org.issuetracking.service.ValidationException;

@Named
@RequestScoped
public class UserBean {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Inject
    private UserService gServ;

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
            gServ.add(user);
            return "/users.xhtml?faces-redirect=true";
        } catch (ValidationException ex) {
            showException(ex);
            return "";
        }
    }

    public String updateUser() {
        try {
            gServ.edit(user);
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

    private void navigate(String where) {
        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

        nav.performNavigation(where);
    }

    private void showException(Exception ex) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validation Error - " + ex.getMessage(), ex.toString()));
    }

}
