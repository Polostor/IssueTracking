package org.issuetracking.view;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.issuetracking.model.User;
import org.issuetracking.service.UserService;

@Named(value = "userBean")
@RequestScoped
public class UserBean {

    private String username;
    private String password;

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
        if (id < 1) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation("new");
            return null;
        }
        user = gServ.getObj(id);
        if (user.getEmail() == null && user.getNick() == null) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation("new");
            return null;
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
        gServ.create(user);
        return "/users.xhtml?faces-redirect=true";
    }

    public String updateUser() {
        gServ.update(user);
        return "/users.xhtml?faces-redirect=true";
    }

    public String init() {
        if (id < 1) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation("new");
            return "new.xhtml?faces-redirect=true";
        }
        user = gServ.getObj(id);
        if (user.getEmail() == null && user.getNick() == null) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

            nav.performNavigation("new");
            return "new.xhtml?faces-redirect=true";
        }
        return "";
    }

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            //context.addMessage(null, new FacesMessage(username + " " + password));
            User user = gServ.find(username);
            //context.addMessage(null, new FacesMessage(user.toString()));
            if (user.getPass() == null ? password != null : !user.getPass().equals(password)) {
                context.addMessage(null, new FacesMessage("Login failed!"));
                return;
            }
            context.addMessage(null, new FacesMessage("Login successful!"));
            externalContext.getSessionMap().put("user", user);
        } catch (Exception e) {
            // Handle unknown username/password in request.login().
            context.addMessage(null, new FacesMessage("Unknown login"));
        }
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
