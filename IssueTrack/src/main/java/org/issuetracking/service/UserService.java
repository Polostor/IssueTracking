package org.issuetracking.service;

import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

public class UserService extends GenericService<User> {

    public String saveUser() {
        gDAO.create(this.obj);
        //addFlashMessage("User " + this.obj.getIduser() + " saved");

        return "users.xhtml?faces-redirect=true";
    }

    public String editUser() {
        gDAO.update(this.obj);
        //addFlashMessage("User " + this.obj.getIduser() + " saved");

        return "users.xhtml?faces-redirect=true";
    }

    public void setUser(User user) {
        this.obj = user;
    }

    public User getUser() {
        return obj;
    }

    @Override
    public void setUp() throws Exception {
        gDAO = new UserDAO();
        super.setUp(); 
    }
}
