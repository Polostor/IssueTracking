package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.User;
import org.issuetracking.service.UserService;

@Named(value = "userBean")
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
        return gServ.getAllObjs();
    }

    public User getUserById() {
        user = gServ.getObj(id);
        return user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String saveUser() {
        gServ.create(user);
        return "list.xhtml?faces-redirect=true";
    }

    public String updateUser() {
        gServ.update(user);
        return "list.xhtml?faces-redirect=true";
    }

    public String init() {
        if (id < 1) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation("new");
            return "new.xhtml";
        }
        user = gServ.getObj(id);
        if(user.getEmail() == null && user.getNick() == null){
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation("new");
            return "new.xhtml";
        }
        return "";
    }

}
