package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
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
        return "list.xhtml";
    }

    public String updateUser() {
        gServ.update(user);
        return "list.xhtml";
    }

    public String init() {
        //System.out.println("id - " + id);
        if (id == 0) {
            return "list.xhtml";
        }
        user = gServ.getObj(id);
        return "";
    }

}
